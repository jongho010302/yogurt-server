package com.yogurt.domain.lecture.service.common;

import com.yogurt.domain.lecture.infra.common.CommonLectureBookingRepository;
import com.yogurt.domain.lecture.infra.common.CommonLectureItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@RequiredArgsConstructor
public class CommonLectureServiceImpl implements CommonLectureService {

    private final CommonLectureItemRepository commonLectureItemRepository;

    private final CommonLectureBookingRepository commonLectureBookingRepository;

    @Transactional
    public boolean existsLectureItemById(Long id) {
        return commonLectureItemRepository.existsById(id);
    }

    @Transactional
    public boolean existsLectureBookingById(Long id) {
        return commonLectureBookingRepository.existsById(id);
    }
}
