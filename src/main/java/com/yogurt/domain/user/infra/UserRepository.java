package com.yogurt.domain.user.infra;

import com.yogurt.generic.user.domain.Email;
import com.yogurt.domain.user.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;


public interface UserRepository extends JpaRepository<User, Long>, UserRepositoryCustom {

    Optional<User> findByEmail(Email email);

    List<User> findByName(String name);

    boolean existsByEmail(Email email);
}
