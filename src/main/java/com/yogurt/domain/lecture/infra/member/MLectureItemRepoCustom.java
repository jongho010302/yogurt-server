package com.yogurt.domain.lecture.infra.member;

import com.yogurt.domain.lecture.domain.LectureItem;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface MLectureItemRepoCustom {
    List<LectureItem> getAllWithFilter(Pageable pageable, Long studioId, String startAt, String endAt, String weekDay, Long staffId, String classType);
}
