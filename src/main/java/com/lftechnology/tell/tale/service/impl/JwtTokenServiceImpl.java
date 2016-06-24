package com.lftechnology.tell.tale.service.impl;

import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SignatureException;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.HashMap;
import java.util.Map;

import com.auth0.jwt.JWTSigner;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.JWTVerifyException;
import com.auth0.jwt.internal.org.apache.commons.codec.binary.Base64;
import com.lftechnology.tell.tale.exception.UnauthorizedException;
import com.lftechnology.tell.tale.service.JwtTokenService;

/**
 * @author Achyut Pokhrel <achyutpokhrel@lftechnology.com>
 * Jun 24, 2016
 * 
 */
public class JwtTokenServiceImpl implements JwtTokenService{
	
	public static final Integer TOKEN_EXPIRE_AT = 10;
    private static final String APP_SECRET_KEY = "telltale";
    private static final String AUDIENCE = "1";

    public static final String ISS = "iss";
    public static final String SUB = "sub";
    public static final String AUD = "aud";
    public static final String EXP = "exp";
    public static final String IAT = "iat";
    
    private static final String ISSUER = "Team The Winners";
    
	@Override
	public String payloadToToken(Map<String, Object> tokenPayload) {
		return new JWTSigner(APP_SECRET_KEY).sign(tokenPayload);
	}

	@Override
	public Map<String, Object> makePayload(String randomText, Integer expiryAfterMinutes) {
		Base64 encoder = new Base64(true);
        String encodedRandomText = encoder.encodeToString(randomText.getBytes());

        LocalDateTime now = LocalDateTime.now();

        return new HashMap<String, Object>(){
        	{
        		put(ISS, ISSUER);
        		put(SUB, encodedRandomText);
        		put(AUD, AUDIENCE);
        		put(EXP, now.plusMinutes(expiryAfterMinutes).atZone(ZoneId.systemDefault()).toEpochSecond());
        		put(IAT, now.atZone(ZoneId.systemDefault()).toEpochSecond());
        	}
        };
	}

	@Override
	public String decodeRandomText(String token) {
		Map<String, Object> map = validate(token);
        String sub = (String) map.get(SUB);
        return new String(Base64.decodeBase64(sub.getBytes()));
	}

	@Override
	public Map<String, Object> validate(String token) {
        try {
            Map<String, Object> decodedPayload = new JWTVerifier(APP_SECRET_KEY, AUDIENCE).verify(token);

            Long exp = Long.valueOf(decodedPayload.get(EXP).toString());
            Instant instant = Instant.ofEpochMilli(exp);
            LocalDateTime expiry = LocalDateTime.ofInstant(instant, ZoneId.systemDefault());

            if (expiry.isAfter(LocalDateTime.now())) {
                throw new UnauthorizedException("Invalid user request");
            }

            return decodedPayload;
        } catch (InvalidKeyException | NoSuchAlgorithmException | IllegalStateException | SignatureException | IOException
                | JWTVerifyException e) {
        	throw new UnauthorizedException("Invalid user request");
        }
	}
}
