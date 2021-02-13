package com.yogurt.domain.studio.service.common;

import com.yogurt.domain.studio.domain.Studio;

public interface CommonStudioService {

    Studio getById(Long id);

    Studio create(Studio studio);

    boolean existsById(Long id);
}
