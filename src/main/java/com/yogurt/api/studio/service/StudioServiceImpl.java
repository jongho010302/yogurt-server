package com.yogurt.api.studio.service;

import com.yogurt.base.exception.YogurtDataNotExistsException;
import com.yogurt.api.studio.domain.Studio;
import com.yogurt.api.studio.infra.StudioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@RequiredArgsConstructor
public class StudioServiceImpl implements StudioService {

    private final StudioRepository repository;

    @Transactional
    public Studio getById(Long id) {
        return repository.findById(id).orElseThrow(() -> new YogurtDataNotExistsException("센터가 존재하지 않습니다."));
    }

    @Transactional
    public List<Studio> getAll() {
        return repository.findAll();
    }

    @Transactional
    public Studio save(Studio studio) {
        return repository.save(studio);
    }

    @Transactional
    public boolean existsById(Long id) {
        return repository.existsById(id);
    }
}
