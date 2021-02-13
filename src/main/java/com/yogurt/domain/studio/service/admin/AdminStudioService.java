package com.yogurt.domain.studio.service.admin;

import com.yogurt.domain.studio.domain.Studio;

import java.util.List;

public interface AdminStudioService {

    Studio getById(Long id);

    Studio create(Studio studio);

    boolean existsById(Long id);
}
