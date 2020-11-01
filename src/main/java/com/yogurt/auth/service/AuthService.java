package com.yogurt.auth.service;

import com.yogurt.auth.dto.FindPasswordRequest;
import com.yogurt.auth.dto.LoginRequest;
import com.yogurt.auth.dto.LoginResponse;
import com.yogurt.auth.dto.SaveUserRequest;
import com.yogurt.user.domain.User;

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
