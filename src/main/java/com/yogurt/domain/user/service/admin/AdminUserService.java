package com.yogurt.domain.user.service.admin;

import com.yogurt.domain.base.model.UserRole;
import com.yogurt.domain.user.domain.User;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface AdminUserService {

    User getById(Long id);

    List<User> getAllWithFilter(Pageable pageable);

    User changeRole(Long id, UserRole.RoleEnum role);
}
