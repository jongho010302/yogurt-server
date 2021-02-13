package com.yogurt.domain.studio.service.admin;

import com.yogurt.domain.studio.domain.Studio;

public interface AdminStudioService {

    Studio getById(Long id);

    Studio create(Studio studio);

    boolean existsById(Long id);
}
