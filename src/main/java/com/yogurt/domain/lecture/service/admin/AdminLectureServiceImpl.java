package com.yogurt.domain.lecture.service.admin;

import com.yogurt.domain.lecture.domain.Lecture;
import com.yogurt.domain.lecture.domain.LectureItem;
import com.yogurt.domain.lecture.dto.admin.LectureSchedule;
import com.yogurt.domain.lecture.dto.admin.LecturesResponse;
import com.yogurt.domain.lecture.dto.admin.SaveLecturesRequest;
import com.yogurt.domain.lecture.infra.admin.ALectureItemRepo;
import com.yogurt.domain.lecture.infra.admin.ALectureRepo;
import com.yogurt.domain.staff.domain.Staff;
import com.yogurt.domain.staff.service.admin.StaffService;
import com.yogurt.util.DateUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AdminLectureServiceImpl implements AdminLectureService {

    private final StaffService staffService;

    private final ALectureRepo ALectureRepo;

    private final ALectureItemRepo lectureItemRepo;

    @Transactional
    public List<LecturesResponse> getAllWithFilter(Long studioId, Pageable pageable, String startAt, String endAt, String weekDay, Long staffId, String classType) {
        return lectureItemRepo.getAllWithFilter(pageable, studioId, startAt, endAt, weekDay, staffId, classType);
    }

    @Transactional
    public Lecture create(SaveLecturesRequest saveLecturesRequest) {
        Lecture lecture = saveLecturesRequest.toLectureEntity();
        Staff staff = staffService.getById(saveLecturesRequest.getStaffId());

        String bookingEndTime = saveLecturesRequest.getBookingEndTime();
        String bookingCancelEndTime = saveLecturesRequest.getBookingCancelEndTime();
        String bookingChangeEndTime = saveLecturesRequest.getBookingChangeEndTime();

        Date startDate = DateUtils.parse(saveLecturesRequest.getStartDate());
        Date endDate = DateUtils.parse(saveLecturesRequest.getEndDate());
        Date currentDate = startDate;

        while (currentDate.compareTo(endDate) <= 0) {
            Calendar currentCalendar = Calendar.getInstance();
            currentCalendar.setTime(currentDate);

            LectureItem lectureItem = saveLecturesRequest.toLectureItemEntity(lecture, staff);

            int currentDayOfWeek = currentCalendar.get(Calendar.DAY_OF_WEEK);
            Optional<LectureSchedule> optionalSchedule = this.getOptionalSchedule(saveLecturesRequest.getSchedules(), currentDayOfWeek);

            // 수업이 있을 경우
            if (optionalSchedule.isPresent()) {
                Calendar scheduleCalendar = (Calendar) currentCalendar.clone();
                LectureSchedule lectureSchedule = optionalSchedule.get();

                String classStartTime = lectureSchedule.getStartTime();
                String classEndTime = lectureSchedule.getEndTime();

                // 수업 시작 시간
                scheduleCalendar.set(Calendar.HOUR, Integer.parseInt(classStartTime.substring(0, 2)));
                scheduleCalendar.set(Calendar.MINUTE, Integer.parseInt(classStartTime.substring(3, 5)));
                lectureItem.setStartAt(DateUtils.format(scheduleCalendar.getTime(), "yyyy-MM-dd HH:mm:ss"));

                // 수업 종료 시간
                scheduleCalendar.set(Calendar.HOUR, Integer.parseInt(classEndTime.substring(0, 2)));
                scheduleCalendar.set(Calendar.MINUTE, Integer.parseInt(classEndTime.substring(3, 5)));
                lectureItem.setEndAt(DateUtils.format(scheduleCalendar.getTime(), "yyyy-MM-dd HH:mm:ss"));

                // 예약 종료 시간
                scheduleCalendar.set(Calendar.HOUR, Integer.parseInt(classStartTime.substring(0, 2)));
                scheduleCalendar.set(Calendar.MINUTE, Integer.parseInt(classStartTime.substring(3, 5)));
                scheduleCalendar.add(Calendar.HOUR, -Integer.parseInt(bookingEndTime.substring(0, 2)));
                scheduleCalendar.add(Calendar.MINUTE, -Integer.parseInt(bookingEndTime.substring(3, 5)));
                lectureItem.setBookingEndAt(DateUtils.format(scheduleCalendar.getTime(), "yyyy-MM-dd HH:mm:ss"));

                // 예약 취소 종료 시간
                scheduleCalendar.set(Calendar.HOUR, Integer.parseInt(classStartTime.substring(0, 2)));
                scheduleCalendar.set(Calendar.MINUTE, Integer.parseInt(classStartTime.substring(3, 5)));
                scheduleCalendar.add(Calendar.HOUR, -Integer.parseInt(bookingCancelEndTime.substring(0, 2)));
                scheduleCalendar.add(Calendar.MINUTE, -Integer.parseInt(bookingCancelEndTime.substring(3, 5)));
                lectureItem.setBookingCancelEndAt(DateUtils.format(scheduleCalendar.getTime(), "yyyy-MM-dd HH:mm:ss"));

                // 예약 변경 종료 시간
                scheduleCalendar.set(Calendar.HOUR, Integer.parseInt(classStartTime.substring(0, 2)));
                scheduleCalendar.set(Calendar.MINUTE, Integer.parseInt(classStartTime.substring(3, 5)));
                scheduleCalendar.add(Calendar.HOUR, -Integer.parseInt(bookingChangeEndTime.substring(0, 2)));
                scheduleCalendar.add(Calendar.MINUTE, -Integer.parseInt(bookingChangeEndTime.substring(3, 5)));
                lectureItem.setBookingChangeEndAt(DateUtils.format(scheduleCalendar.getTime(), "yyyy-MM-dd HH:mm:ss"));

                lecture.getLectureItems().add(lectureItem);
            }

            addOneDay(currentCalendar);
            currentDate = currentCalendar.getTime();
        }

        Lecture savedLecture = ALectureRepo.save(lecture);
        return savedLecture;
    }

    /**
     * 수업이 있는 날인지 검색한다.
     */
    private Optional<LectureSchedule> getOptionalSchedule(List<LectureSchedule> schedules, int currentDayOfWeek) {
        Optional<LectureSchedule> optionalSchedule = schedules
                .stream()
                .filter(schedule -> schedule.getHasClass() && schedule.getDayOfWeek() == currentDayOfWeek)
                .findAny();
        return optionalSchedule;
    }

    private void addOneDay(Calendar calendar) {
        calendar.add(Calendar.DAY_OF_MONTH, 1);
    }
}
