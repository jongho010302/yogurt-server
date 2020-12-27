package com.yogurt.api.user.infra;

import com.yogurt.api.user.domain.User;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface UserRepositoryCustom {

    List<User> getAllWithFilter(Pageable pageable, Boolean isDeleted);
}
