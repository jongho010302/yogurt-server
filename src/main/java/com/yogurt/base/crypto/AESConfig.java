package com.yogurt.base.crypto;

import javax.crypto.spec.IvParameterSpec;
import java.io.UnsupportedEncodingException;

public class AESConfig {

    private String mode = "AES/CBC/PKCS5Padding";
    private String key;
    private String algorithm = "AES";

    public AESConfig() {
    }

    public AESConfig(String mode, String key, String algorithm) {
        this.mode = mode;
        this.key = key;
        this.algorithm = algorithm;
    }

    public String getMode() {
        return mode;
    }

    public void setMode(String mode) {
        this.mode = mode;
    }

    public String getAlgorithm() {
        return algorithm;
    }

    public void setAlgorithm(String algorithm) {
        this.algorithm = algorithm;
    }

    public String getKey() {
        return Base64Util.decodeString(key);
    }

    public void setKey(String key) {
        this.key = key;
    }

    public IvParameterSpec getIv() throws UnsupportedEncodingException {
//		String decodeKey = Base64Util.decodeString(key);
//		if (decodeKey.length() > 16) decodeKey = decodeKey.substring(0, 16);
//		return new IvParameterSpec(decodeKey.getBytes("UTF-8"));
        return new IvParameterSpec("0000000000000000".getBytes("UTF-8"));
    }

}
