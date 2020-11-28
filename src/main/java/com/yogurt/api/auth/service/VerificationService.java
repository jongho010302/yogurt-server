package com.yogurt.api.auth.service;

import com.yogurt.api.auth.dto.SendVerificationCodeRequest;

public interface VerificationService {

    void sendVerificationCode(SendVerificationCodeRequest sendVerificationCodeRequest);

    void verifyEmail(String email, String verificationCode, String type);
}
