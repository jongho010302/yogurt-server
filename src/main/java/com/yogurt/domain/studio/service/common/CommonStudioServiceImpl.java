package com.yogurt.domain.studio.service.common;

import com.yogurt.domain.studio.domain.Studio;
import com.yogurt.domain.studio.exception.StudioNotFoundException;
import com.yogurt.domain.studio.infra.StudioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@RequiredArgsConstructor
public class CommonStudioServiceImpl implements CommonStudioService {

    private final StudioRepository repository;

    @Transactional
    public Studio getById(Long id) {
        return repository.findById(id).orElseThrow(() -> new StudioNotFoundException(id));
    }

    @Transactional
    public Studio create(Studio studio) {
        return repository.save(studio);
    }

    @Transactional
    public boolean existsById(Long id) {
        return repository.existsById(id);
    }
}
