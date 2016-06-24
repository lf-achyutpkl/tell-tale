package com.lftechnology.tell.tale.service.impl;

import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;

import javax.crypto.Cipher;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESedeKeySpec;

import com.lftechnology.tell.tale.exception.EncryptionException;
import com.lftechnology.tell.tale.service.EncryptionDecryptionService;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

/**
 * @author Achyut Pokhrel <achyutpokhrel@lftechnology.com>
 * Jun 24, 2016
 * 
 */
public class EncryptionDecryptionServiceImpl implements EncryptionDecryptionService{
	
	private static final String UNICODE_FORMAT = "UTF8";
    public static final String DESEDE_ENCRYPTION_SCHEME = "DESede";
    private static final String ALGORITHM_KEY = "ThisIsSecretEncryptionKey";
    private KeySpec keySpec; 
    private SecretKeyFactory mySecretKeyFactory;
    private Cipher cipher;
    byte[] keyAsBytes;
    SecretKey key;
    
    public EncryptionDecryptionServiceImpl() {
    	try {
			cipher = Cipher.getInstance(DESEDE_ENCRYPTION_SCHEME);
		} catch (Exception e) {
			throw new EncryptionException();
		}
    }
    
	@SuppressWarnings("restriction")
	@Override
	public String encrypt(String unencryptedString, String key) {
		String encryptedString = null;
        try {
            cipher.init(Cipher.ENCRYPT_MODE, createKey(key));
            byte[] plainText = unencryptedString.getBytes(UNICODE_FORMAT);
            byte[] encryptedText = cipher.doFinal(plainText);
            BASE64Encoder base64encoder = new BASE64Encoder();
            encryptedString = base64encoder.encode(encryptedText);
        } catch (Exception e) {
           throw new EncryptionException();
        }
        return encryptedString;
	}

	@Override
	public String decrypt(String encryptedString, String key) {
		String decryptedText=null;
        try {
            cipher.init(Cipher.DECRYPT_MODE, createKey(key));
            BASE64Decoder base64decoder = new BASE64Decoder();
            byte[] encryptedText = base64decoder.decodeBuffer(encryptedString);
            byte[] plainText = cipher.doFinal(encryptedText);
            decryptedText= bytes2String(plainText);
        } catch (Exception e) {
        	throw new EncryptionException();
        }
        return decryptedText;
	}
	
	private SecretKey createKey(String userKey) throws UnsupportedEncodingException, InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeySpecException{
		keyAsBytes = userKey.getBytes(UNICODE_FORMAT);
	    keySpec = new DESedeKeySpec(keyAsBytes);
	    mySecretKeyFactory = SecretKeyFactory.getInstance(DESEDE_ENCRYPTION_SCHEME);
	    return mySecretKeyFactory.generateSecret(keySpec);
	}
	
	private String bytes2String(byte[] bytes) {
        StringBuffer stringBuffer = new StringBuffer();
        for (int i = 0; i < bytes.length; i++) {
            stringBuffer.append((char) bytes[i]);
        }
        return stringBuffer.toString();
    }
}
