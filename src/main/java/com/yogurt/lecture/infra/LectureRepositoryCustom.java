package com.yogurt.lecture.infra;

import com.yogurt.lecture.domain.LectureItem;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface LectureRepositoryCustom {

    List<LectureItem> getAllWithFilter(Pageable pageable, Long studioId, String startAt, String endAt, String weekDay, Long staffId, String classType);


}
