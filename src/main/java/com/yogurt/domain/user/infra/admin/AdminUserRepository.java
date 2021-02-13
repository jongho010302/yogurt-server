package com.yogurt.domain.user.infra.admin;

import com.yogurt.domain.user.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;


public interface AdminUserRepository extends JpaRepository<User, Long>, AdminUserRepositoryCustom {
}
