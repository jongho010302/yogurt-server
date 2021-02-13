package com.yogurt.domain.user.service.admin;

import com.yogurt.domain.base.model.UserRole;
import com.yogurt.domain.user.domain.User;
import com.yogurt.domain.user.infra.admin.AdminUserRepository;
import com.yogurt.domain.user.service.common.CommonUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AdminUserServiceImpl implements AdminUserService {

    private final CommonUserService commonUserService;

    private final AdminUserRepository repository;

    @Transactional
    public User getById(Long id) {
        return commonUserService.getById(id);
    }

    public List<User> getAllWithFilter(Pageable pageable) {
        return repository.getAllWithFilter(pageable);
    }

    @Transactional
    public User changeRole(Long id, UserRole.RoleEnum role) {
        User user = this.getById(id);
        user.setRole(role);
        return repository.save(user);
    }
}
