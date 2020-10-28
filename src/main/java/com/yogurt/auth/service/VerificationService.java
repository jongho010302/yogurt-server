package com.yogurt.auth.service;

import com.yogurt.auth.dto.SendVerificationCodeRequest;

public interface VerificationService {

    void sendVerificationCode(SendVerificationCodeRequest sendVerificationCodeRequest);

    void verifyEmail(String email, String verificationCode, String type);
}
