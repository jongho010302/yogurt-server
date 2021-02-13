package com.yogurt.domain.studio.service.admin;

import com.yogurt.domain.studio.domain.Studio;
import com.yogurt.domain.studio.service.common.CommonStudioService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@RequiredArgsConstructor
public class AdminStudioServiceImpl implements AdminStudioService {

    private final CommonStudioService commonStudioService;

    @Transactional
    public Studio getById(Long id) {
        return commonStudioService.getById(id);
    }

    @Transactional
    public Studio create(Studio studio) {
        return commonStudioService.create(studio);
    }

    @Transactional
    public boolean existsById(Long id) {
        return commonStudioService.existsById(id);
    }
}
