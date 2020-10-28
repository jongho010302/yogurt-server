package com.yogurt.base.security;

import com.yogurt.base.exception.YogurtDataNotExistsException;
import com.yogurt.user.domain.User;
import com.yogurt.user.domain.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class UserDetailService {

    private final UserRepository userRepository;

    public User getByUsername(String username) {
        return userRepository.findByUsername(username).orElseThrow(() -> new YogurtDataNotExistsException("사용자를 찾을 수 없습니다."));
    }
}