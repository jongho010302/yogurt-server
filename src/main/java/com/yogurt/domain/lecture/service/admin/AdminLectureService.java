package com.yogurt.domain.lecture.service.admin;

import com.yogurt.domain.lecture.domain.Lecture;
import com.yogurt.domain.lecture.domain.LectureItem;
import com.yogurt.domain.lecture.dto.admin.LecturesResponse;
import com.yogurt.domain.lecture.dto.admin.SaveLecturesRequest;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface AdminLectureService {
    Lecture create(SaveLecturesRequest saveLecturesRequest);

    List<LecturesResponse> getAllWithFilter(Long studioId, Pageable pageable, String startAt, String endAt, String weekDay, Long staffId, String classType);
}
