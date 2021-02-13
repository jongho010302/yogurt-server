package com.yogurt.domain.user.service.common;

import com.yogurt.domain.user.domain.User;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface CommonUserService {

    User getById(Long id);

    User getByEmail(String Email);

    boolean existsById(Long id);

    boolean existsByEmail(String email);
}
