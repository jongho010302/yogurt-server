package com.yogurt.domain.user.service.common;

import com.yogurt.domain.user.domain.User;

public interface CommonUserService {

    User getById(Long id);

    User getByEmail(String Email);

    boolean existsById(Long id);

    boolean existsByEmail(String email);
}
