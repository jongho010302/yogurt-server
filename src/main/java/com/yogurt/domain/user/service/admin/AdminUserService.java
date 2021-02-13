package com.yogurt.domain.user.service.admin;

import com.yogurt.domain.user.domain.User;
import com.yogurt.generic.user.domain.UserRole;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface AdminUserService {

    User getById(Long id);

    List<User> getAllWithFilter(Pageable pageable, Boolean isDeleted);

    User changeRole(Long id, UserRole.RoleEnum role);
}
