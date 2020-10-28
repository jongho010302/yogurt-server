package com.yogurt.user.domain;

import com.yogurt.generic.user.domain.Email;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;


public interface UserRepository extends JpaRepository<User, Long>, UserRepositoryCustom {

    Optional<User> findByUsername(String username);

    Optional<User> findByEmail(Email email);

    List<User> findByName(String name);

    boolean existsByUsername(String username);

    boolean existsByEmail(Email email);
}
