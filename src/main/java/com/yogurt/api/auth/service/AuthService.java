package com.yogurt.api.auth.service;

import com.yogurt.api.auth.dto.FindPasswordRequest;
import com.yogurt.api.auth.dto.LoginRequest;
import com.yogurt.api.auth.dto.LoginResponse;
import com.yogurt.api.auth.dto.SaveUserRequest;
import com.yogurt.api.user.domain.User;

import java.util.List;

public interface AuthService {

    LoginResponse login(LoginRequest loginRequest);

    User saveUser(SaveUserRequest saveUserRequest);

    void validateUsername(String username);

    List<String> findMaskingUsername(String username);

    void findUsername(String email);

    void findPassword(FindPasswordRequest findPasswordRequest);

    String getRandomPassword();
}
