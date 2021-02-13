package com.yogurt.domain.user.service.common;

import com.yogurt.domain.base.model.Email;
import com.yogurt.domain.user.domain.User;
import com.yogurt.domain.user.exception.UserEmailNotFoundException;
import com.yogurt.domain.user.exception.UserNotFoundException;
import com.yogurt.domain.user.infra.common.CommonUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@RequiredArgsConstructor
public class CommonUserServiceImpl implements CommonUserService {

    private final CommonUserRepository repository;

    @Transactional
    public User getById(Long id) {
        return repository.findById(id).orElseThrow(() -> new UserNotFoundException(id));
    }

    public User getByEmail(String email) {
        return repository.findByEmail(Email.of(email)).orElseThrow(() -> new UserEmailNotFoundException(email));
    }

    public boolean existsByEmail(String email) {
        return repository.existsByEmail(Email.of(email));
    }

    @Transactional
    public boolean existsById(Long id) {
        return repository.existsById(id);
    }
}
