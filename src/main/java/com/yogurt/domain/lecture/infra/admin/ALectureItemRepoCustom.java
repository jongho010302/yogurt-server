package com.yogurt.domain.lecture.infra.admin;

import com.yogurt.domain.lecture.domain.LectureItem;
import com.yogurt.domain.lecture.dto.admin.LecturesResponse;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ALectureItemRepoCustom {
    List<LecturesResponse> getAllWithFilter(Pageable pageable, Long studioId, String startAt, String endAt, String weekDay, Long staffId, String classType);
}
