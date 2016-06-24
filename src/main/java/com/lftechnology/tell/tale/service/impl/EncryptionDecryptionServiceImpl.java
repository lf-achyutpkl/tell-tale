package com.lftechnology.tell.tale.service.impl;

import java.security.spec.KeySpec;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESedeKeySpec;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import com.lftechnology.tell.tale.exception.EncryptionException;
import com.lftechnology.tell.tale.service.EncryptionDecryptionService;

/**
 * @author Achyut Pokhrel <achyutpokhrel@lftechnology.com> Jun 24, 2016
 * 
 */
public class EncryptionDecryptionServiceImpl implements EncryptionDecryptionService {

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
        String encryptedString = "";
        try {
            cipher = Cipher.getInstance(DESEDE_ENCRYPTION_SCHEME);
            cipher.init(Cipher.ENCRYPT_MODE, generateKey(formKey(key)));
            byte[] plainText = unencryptedString.getBytes(UNICODE_FORMAT);
            byte[] encryptedText = cipher.doFinal(plainText);
            BASE64Encoder base64encoder = new BASE64Encoder();
            encryptedString = base64encoder.encode(encryptedText);
        } catch (Exception e) {
            e.printStackTrace();
            throw new EncryptionException();
        }
        return encryptedString;
    }

    @Override
    public String decrypt(String encryptedString, String key) {
        String decryptedText = null;
        try {
            cipher = Cipher.getInstance(DESEDE_ENCRYPTION_SCHEME);
            cipher.init(Cipher.DECRYPT_MODE, generateKey(formKey(key)));
            BASE64Decoder base64decoder = new BASE64Decoder();
            byte[] encryptedText = base64decoder.decodeBuffer(encryptedString);
            byte[] plainText = cipher.doFinal(encryptedText);
            decryptedText = bytes2String(plainText);
        } catch (Exception e) {
            throw new EncryptionException();
        }
        return decryptedText;
    }

    private SecretKey generateKey(String key) {
        try {
            keyAsBytes = key.getBytes(UNICODE_FORMAT);
            keySpec = new DESedeKeySpec(keyAsBytes);
            mySecretKeyFactory = SecretKeyFactory.getInstance(DESEDE_ENCRYPTION_SCHEME);
            return mySecretKeyFactory.generateSecret(keySpec);
        } catch (Exception e) {
            throw new EncryptionException();
        }

    }

    private String bytes2String(byte[] bytes) {
        StringBuffer stringBuffer = new StringBuffer();
        for (int i = 0; i < bytes.length; i++) {
            stringBuffer.append((char) bytes[i]);
        }
        return stringBuffer.toString();
    }

    private String formKey(String key) {
        int appendSize = 25 - key.length();
        for (int i = 0; i < appendSize; i++) {
            key+="-";
        }
        return key;
    }
}
