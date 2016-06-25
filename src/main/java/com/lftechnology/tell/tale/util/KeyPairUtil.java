package com.lftechnology.tell.tale.util;

import java.security.GeneralSecurityException;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Arrays;

import javax.crypto.Cipher;

import org.apache.commons.codec.binary.Base64;

/**
 * 
 * @author Prajjwal Raj Kandel<prajjwalkandel@lftechnology.com>
 *
 */
public class KeyPairUtil {

    public static byte[] encrypt(byte[] inpBytes, PublicKey key, String xform) throws Exception {
        Cipher cipher = Cipher.getInstance(xform);
        cipher.init(Cipher.ENCRYPT_MODE, key);
        return cipher.doFinal(inpBytes);
    }

    public static byte[] decrypt(byte[] inpBytes, PrivateKey key, String xform) throws Exception {
        Cipher cipher = Cipher.getInstance(xform);
        cipher.init(Cipher.DECRYPT_MODE, key);
        return cipher.doFinal(inpBytes);
    }

    public static KeyPair generateKeyPair() throws Exception {
        // Generate a key-pair
        KeyPairGenerator kpg = KeyPairGenerator.getInstance("RSA");
        kpg.initialize(512); // 512 is the keysize.
        KeyPair kp = kpg.generateKeyPair();
        return kp;
    }

    public static PrivateKey convertStringToPrivateKey(String key64) throws GeneralSecurityException {
        byte[] clear = Base64.decodeBase64(key64);
        PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(clear);
        KeyFactory fact = KeyFactory.getInstance("RSA");
        PrivateKey priv = fact.generatePrivate(keySpec);
        Arrays.fill(clear, (byte) 0);
        return priv;
    }

    public static PublicKey convertStringToPublicKey(String stored) throws GeneralSecurityException {
        byte[] data = Base64.decodeBase64(stored);
        X509EncodedKeySpec spec = new X509EncodedKeySpec(data);
        KeyFactory fact = KeyFactory.getInstance("RSA");
        return fact.generatePublic(spec);
    }

    public static String convertPrivateKeyToString(PrivateKey priv) throws GeneralSecurityException {
        KeyFactory fact = KeyFactory.getInstance("RSA");
        PKCS8EncodedKeySpec spec = fact.getKeySpec(priv, PKCS8EncodedKeySpec.class);
        byte[] packed = spec.getEncoded();
        String key64 = Base64.encodeBase64String(packed);

        Arrays.fill(packed, (byte) 0);
        return key64;
    }

    public static String convertPublicKeyToString(PublicKey publ) throws GeneralSecurityException {
        KeyFactory fact = KeyFactory.getInstance("RSA");
        X509EncodedKeySpec spec = fact.getKeySpec(publ, X509EncodedKeySpec.class);
        return Base64.encodeBase64String(spec.getEncoded());
    }
}
