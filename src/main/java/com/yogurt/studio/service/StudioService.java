package com.yogurt.studio.service;

import com.yogurt.studio.domain.Studio;

import java.util.List;

public interface StudioService {

    Studio getById(Long id);

    List<Studio> getAll();

    Studio save(Studio studio);

    boolean existsById(Long id);
}
