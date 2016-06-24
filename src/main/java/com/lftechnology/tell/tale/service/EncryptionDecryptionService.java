package com.lftechnology.tell.tale.service;

/**
 * @author Achyut Pokhrel <achyutpokhrel@lftechnology.com>
 * Jun 24, 2016
 * 
 */
public interface EncryptionDecryptionService {
	
	public String encrypt(String unencryptedString, String key);
	
	public String decrypt(String encryptedString, String key);
}
