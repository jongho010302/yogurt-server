package com.yogurt.api.auth.service;

import com.yogurt.api.auth.dto.FindPasswordRequest;
import com.yogurt.api.auth.dto.LoginRequest;
import com.yogurt.api.auth.dto.LoginResponse;
import com.yogurt.api.auth.dto.SaveUserRequest;
import com.yogurt.api.user.domain.User;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public interface AuthService {

    LoginResponse login(LoginRequest loginRequest);

    void logout(HttpServletRequest request);

    User saveUser(SaveUserRequest saveUserRequest);

    void findPassword(FindPasswordRequest findPasswordRequest);

    String getRandomPassword();
}
