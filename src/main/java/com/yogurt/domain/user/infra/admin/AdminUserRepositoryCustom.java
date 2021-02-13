package com.yogurt.domain.user.infra.admin;

import com.yogurt.domain.user.domain.User;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface AdminUserRepositoryCustom {

    List<User> getAllWithFilter(Pageable pageable);
}
