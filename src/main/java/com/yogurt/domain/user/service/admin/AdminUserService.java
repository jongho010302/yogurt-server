package com.yogurt.domain.user.service.admin;

import com.yogurt.domain.base.model.UserRole;
import com.yogurt.domain.user.domain.User;
import com.yogurt.domain.user.dto.admin.response.UsersResponse;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface AdminUserService {

    User getById(Long id);

    List<UsersResponse> getAllWithFilter(Pageable pageable);

    User changeRole(Long id, UserRole.RoleEnum role);
}
