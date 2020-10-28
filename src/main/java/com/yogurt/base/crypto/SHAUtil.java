package com.yogurt.base.crypto;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class SHAUtil {

    private static final String CRYPT_ALGORITHM = "SHA-512";
    private static final String CRYPT_CHARSET = "UTF-8";


    public static String encrypt(String input) throws UnsupportedEncodingException, NoSuchAlgorithmException {
        return encrypt(CRYPT_ALGORITHM, CRYPT_CHARSET, input);
    }

    public static String encrypt(String algorithm, String charSet, String input) throws UnsupportedEncodingException, NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance(algorithm);
        md.update(input.getBytes(charSet));
        byte byteData[] = md.digest();

        //convert the byte to hex format method 1
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < byteData.length; i++) {
            sb.append(Integer.toString((byteData[i] & 0xff) + 0x100, 16).substring(1));
        }
        return sb.toString();
    }

}
