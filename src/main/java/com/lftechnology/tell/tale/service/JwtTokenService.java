package com.lftechnology.tell.tale.service;

import java.util.Map;

import com.lftechnology.tell.tale.entity.User;

/**
 * @author Achyut Pokhrel <achyutpokhrel@lftechnology.com>
 * Jun 24, 2016
 * 
 */
public interface JwtTokenService {
	
	public String payloadToToken(Map<String, Object> tokenPayload);
	
	public Map<String, Object> makePayload(User user, String randomText, Integer expiryAfterMinutes);
	
	public String[] decodePayLoad(String token);
	
	public Map<String, Object> validate(String token);
}
