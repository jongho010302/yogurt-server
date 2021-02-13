package com.yogurt.global.crypto;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;
import java.io.UnsupportedEncodingException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;


public class AESUtil {

    private static Key createSecretKey(AESConfig aesConfig) {
        return new SecretKeySpec(aesConfig.getKey().getBytes(), aesConfig.getAlgorithm());
    }

    public static byte[] encrypt(byte[] src, AESConfig aesConfig) throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException, InvalidAlgorithmParameterException, UnsupportedEncodingException {
        Cipher cipher = Cipher.getInstance(aesConfig.getMode());
        cipher.init(Cipher.ENCRYPT_MODE, createSecretKey(aesConfig), aesConfig.getIv());
        return cipher.doFinal(src);
    }

    public static String encryptString(byte[] src, AESConfig aesConfig) throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException, InvalidAlgorithmParameterException, UnsupportedEncodingException {
        return new String(encrypt(src, aesConfig));
    }

    public static byte[] encrypt(byte[] src, String mode, String key, String algorithm) throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException, InvalidAlgorithmParameterException, UnsupportedEncodingException {
        AESConfig aesConfig = new AESConfig(mode, key, algorithm);
        return encrypt(src, aesConfig);
    }

    public static String encryptString(byte[] src, String mode, String key, String algorithm) throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException, InvalidAlgorithmParameterException, UnsupportedEncodingException {
        return new String(encrypt(src, mode, key, algorithm));
    }

    public static byte[] decrypt(byte[] src, AESConfig aesConfig) throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException, InvalidAlgorithmParameterException, UnsupportedEncodingException {
        Cipher cipher = Cipher.getInstance(aesConfig.getMode());
        cipher.init(Cipher.DECRYPT_MODE, createSecretKey(aesConfig), aesConfig.getIv());
        return cipher.doFinal(src);
    }

    public static String decryptString(byte[] src, AESConfig aesConfig) throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException, InvalidAlgorithmParameterException, UnsupportedEncodingException {
        return new String(decrypt(src, aesConfig));
    }

    public static byte[] decrypt(byte[] src, String mode, String key, String algorithm) throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException, InvalidAlgorithmParameterException, UnsupportedEncodingException {
        AESConfig aesConfig = new AESConfig(mode, key, algorithm);
        return decrypt(src, aesConfig);
    }

    public static String decryptString(byte[] src, String mode, String key, String algorithm) throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException, InvalidAlgorithmParameterException, UnsupportedEncodingException {
        return new String(decrypt(src, mode, key, algorithm));
    }

}