package com.yogurt.api.studio.service;

import com.yogurt.api.studio.domain.Studio;

import java.util.List;

public interface StudioService {

    Studio getById(Long id);

    List<Studio> getAll();

    Studio save(Studio studio);

    boolean existsById(Long id);
}
