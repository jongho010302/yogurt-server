package com.yogurt.domain.user.service.admin;

import com.yogurt.domain.user.domain.User;
import com.yogurt.domain.user.infra.UserRepository;
import com.yogurt.domain.user.service.common.CommonUserService;
import com.yogurt.generic.user.domain.UserRole;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AdminUserServiceImpl implements AdminUserService {

    private final CommonUserService commonUserService;

    private final UserRepository repository;

    @Transactional
    public User getById(Long id) {
        return commonUserService.getById(id);
    }

    public List<User> getAllWithFilter(Pageable pageable, Boolean isDeleted) {
        return repository.getAllWithFilter(pageable, isDeleted);
    }

    @Transactional
    public User changeRole(Long id, UserRole.RoleEnum role) {
        User user = this.getById(id);
        user.setRole(role);
        return repository.save(user);
    }
}
