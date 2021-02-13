package com.yogurt.domain.lecture.service.admin;

import com.yogurt.domain.lecture.domain.Lecture;
import com.yogurt.domain.lecture.domain.LectureBooking;
import com.yogurt.domain.lecture.domain.LectureItem;
import com.yogurt.domain.lecture.dto.SaveLecturesRequest;
import com.yogurt.domain.user.domain.User;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface AdminLectureService {
    Lecture create(SaveLecturesRequest saveLecturesRequest);
}
