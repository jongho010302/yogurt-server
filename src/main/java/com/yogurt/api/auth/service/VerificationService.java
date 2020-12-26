package com.yogurt.api.auth.service;

public interface VerificationService {

    void sendSignupCode(String email);

    void verifySignupCode(String email, String verificationCode, String type);

    void sendFindPasswordCode(String email);

    void verifyFindPasswordCode(String email, String verificationCode, String type);

    void sendChangeEmailCode(String email);

    void verifyChangeEmailCode(String email, String verificationCode, String type);

    void verifyEmail(String email, String verificationCode, String type);
}
