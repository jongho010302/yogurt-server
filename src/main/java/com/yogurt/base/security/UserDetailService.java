package com.yogurt.base.security;

import com.yogurt.base.exception.YogurtDataNotExistsException;
import com.yogurt.domain.user.domain.User;
import com.yogurt.domain.user.infra.UserRepository;
import com.yogurt.generic.user.domain.Email;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class UserDetailService {

    private final UserRepository userRepository;

    public User getByEmail(String email) {
        return userRepository.findByEmail(Email.of(email)).orElseThrow(() -> new YogurtDataNotExistsException("사용자를 찾을 수 없습니다."));
    }
}