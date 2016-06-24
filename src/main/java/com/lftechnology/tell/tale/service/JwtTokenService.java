package com.lftechnology.tell.tale.service;

import java.util.Map;

/**
 * @author Achyut Pokhrel <achyutpokhrel@lftechnology.com>
 * Jun 24, 2016
 * 
 */
public interface JwtTokenService {
	
	public String payloadToToken(Map<String, Object> tokenPayload);
	
	public Map<String, Object> makePayload(String randomText, Integer expiryAfterMinutes);
	
	public String decodeRandomText(String token);
	
	public Map<String, Object> validate(String token);
}
