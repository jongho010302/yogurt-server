package com.yogurt.domain.auth.service;

public interface VerificationService {

    void sendSignupCode(String email);

    void verifySignupCode(String email, String code);

    void sendFindPasswordCode(String email);

    void verifyFindPasswordCode(String email, String code);

    void sendChangeEmailCode(String email);

    void verifyChangeEmailCode(String email, String code);

    void verifyEmail(String email, String code, String type);
}
