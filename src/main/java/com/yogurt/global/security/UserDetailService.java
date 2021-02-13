package com.yogurt.global.security;

import com.yogurt.domain.user.domain.User;
import com.yogurt.domain.user.service.common.CommonUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class UserDetailService {

    private final CommonUserService commonUserService;

    public User getByEmail(String email) {
        return commonUserService.getByEmail(email);
    }
}