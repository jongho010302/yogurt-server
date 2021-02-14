package com.yogurt.domain.user.infra.common;

import com.yogurt.domain.base.model.Email;
import com.yogurt.domain.user.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface UserRepo extends JpaRepository<User, Long> {
    Optional<User> findByEmail(Email email);

    boolean existsByEmail(Email email);
}
