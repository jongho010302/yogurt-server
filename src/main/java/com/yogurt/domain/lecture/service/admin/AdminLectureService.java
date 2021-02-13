package com.yogurt.domain.lecture.service.admin;

import com.yogurt.domain.lecture.domain.Lecture;
import com.yogurt.domain.lecture.dto.SaveLecturesRequest;

public interface AdminLectureService {
    Lecture create(SaveLecturesRequest saveLecturesRequest);
}
