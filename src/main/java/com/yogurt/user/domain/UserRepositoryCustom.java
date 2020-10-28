package com.yogurt.user.domain;

import org.springframework.data.domain.Pageable;

import java.util.List;

public interface UserRepositoryCustom {

    List<User> getAllWithFilter(Pageable pageable, Boolean isExit);
}
