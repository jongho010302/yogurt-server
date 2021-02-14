package com.yogurt.domain.user.infra.admin;

import com.yogurt.domain.user.dto.admin.response.UsersResponse;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface AUserRepoCustom {

    List<UsersResponse> getAllWithFilter(Pageable pageable);
}
