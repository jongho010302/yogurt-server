package com.yogurt.auth.service;

import com.yogurt.auth.dto.FindPasswordRequest;
import com.yogurt.auth.dto.LoginRequest;
import com.yogurt.auth.dto.LoginResponse;
import com.yogurt.auth.dto.SaveMemberRequest;
import com.yogurt.member.domain.Member;
import com.yogurt.studio.domain.Studio;

import java.util.List;

public interface AuthService {
    List<Studio> getStudioList();

    LoginResponse login(LoginRequest loginRequest);

    Member saveMember(SaveMemberRequest saveMemberRequest);

    void validateUsername(String username);

    List<String> findMaskingUsername(String username);

    void findUsername(String email);

    void findPassword(FindPasswordRequest findPasswordRequest);

    String getRandomPassword();
}
