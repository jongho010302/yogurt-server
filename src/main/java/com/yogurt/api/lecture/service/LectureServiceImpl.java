package com.yogurt.api.lecture.service;

import com.yogurt.api.lecture.dto.LectureSchedule;
import com.yogurt.api.ticket.infra.UserTicketRepository;
import com.yogurt.api.user.domain.User;
import com.yogurt.base.exception.YogurtAlreadyBookedException;
import com.yogurt.base.exception.YogurtBookingEntryExceedException;
import com.yogurt.base.exception.YogurtDataNotExistsException;
import com.yogurt.base.util.DateUtils;
import com.yogurt.api.lecture.domain.Lecture;
import com.yogurt.api.lecture.domain.LectureBooking;
import com.yogurt.api.lecture.domain.LectureItem;
import com.yogurt.api.lecture.dto.SaveLecturesRequest;
import com.yogurt.api.lecture.infra.LectureBookingRepository;
import com.yogurt.api.lecture.infra.LectureItemRepository;
import com.yogurt.api.lecture.infra.LectureRepository;
import com.yogurt.api.staff.domain.Staff;
import com.yogurt.api.staff.service.StaffService;
import com.yogurt.api.ticket.domain.UserTicket;
import com.yogurt.api.ticket.service.UserTicketService;
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
public class LectureServiceImpl implements LectureService {

    private final StaffService staffService;

    private final UserTicketService userTicketService;

    private final LectureRepository lectureRepository;

    private final LectureItemRepository lectureItemRepository;

    private final LectureBookingRepository lectureBookingRepository;

    @Transactional
    public List<LectureItem> getAllWithFilter(Long studioId, Pageable pageable, String startAt, String endAt, String weekDay, Long staffId, String classType) {
        return lectureItemRepository.getAllWithFilter(pageable, studioId, startAt, endAt, weekDay, staffId, classType);
    }

    @Transactional
    public Lecture save(SaveLecturesRequest saveLecturesRequest) {
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

        Lecture savedLecture = lectureRepository.save(lecture);
        return savedLecture;
    }

    /**
     * 수업이 있는 날인지 검색한다.
     */
    private Optional<LectureSchedule> getOptionalSchedule(List<LectureSchedule> schedules, int currentDayOfWeek) {
        Optional<LectureSchedule> optionalSchedule = schedules
                .stream()
                .filter(schedule -> schedule.getHasClass() && schedule.getDayOfWeek() == currentDayOfWeek)
                .findFirst();
        return optionalSchedule;
    }

    private void addOneDay(Calendar calendar) {
        calendar.add(Calendar.DAY_OF_MONTH, 1);
    }

    @Transactional
    public List<LectureBooking> getBookingList(User user, Long userTicketId) {
        List<UserTicket> userTickets = userTicketService.getAllByUser(user);
        List<LectureBooking> lectureBookings = lectureBookingRepository.findByUserTicketInAndIsCanceled(userTickets, false);
        return lectureBookings;
    }

    @Transactional
    public LectureBooking book(Long userId, Long lectureItemId, Long userTicketId) {
        UserTicket userTicket = userTicketService.getById(userTicketId);
        userTicket.validateUserOwner(userId);
        userTicket.validateRemainingCoupon();
        userTicket.validateExpirationDate();

        LectureItem lectureItem = this.getLectureItemById(lectureItemId);
        lectureItem.validateExceedLectureBookingTime();

        this.validateBooking(userId, lectureItem);

        userTicket.booked();
        UserTicket savedUserTicket = userTicketService.save(userTicket);

        LectureBooking lectureBooking = LectureBooking.builder()
                .userTicket(savedUserTicket)
                .lectureItem(lectureItem)
                .isCanceled(false)
                .isAttended(false)
                .build();
        return lectureBookingRepository.save(lectureBooking);
    }

    private LectureItem getLectureItemById(Long id) {
        return lectureItemRepository.findById(id).orElseThrow(() -> new YogurtDataNotExistsException("강의를 찾을 수 없습니다."));
    }

    private void validateBooking(Long userId, LectureItem lectureItem) {
        List<LectureBooking> lectureBookingList = lectureBookingRepository.findByLectureItem(lectureItem);
        this.validateUserAlreadyBooked(userId, lectureBookingList);
        this.validateBookingEntry(lectureItem, lectureBookingList);
    }

    private void validateBookingEntry(LectureItem lectureItem, List<LectureBooking> lectureBookingList) {
        int bookedCount = 0;
        for (LectureBooking lectureBooking : lectureBookingList) {
            if (!lectureBooking.getIsCanceled()) {
                bookedCount++;
            }
        }
        if (bookedCount >= lectureItem.getMaxTrainee()) {
            throw new YogurtBookingEntryExceedException("예약 가능 정원을 초과했습니다. 다시 이용해주세요.");
        }
    }

    private void validateUserAlreadyBooked(Long userId, List<LectureBooking> lectureBookingList) {
        for (LectureBooking lectureBooking : lectureBookingList) {
            if (!lectureBooking.getIsCanceled()) {
                boolean isUserBooked = lectureBooking.getUserTicket().getUser().getId().equals(userId);
                if (isUserBooked) {
                    throw new YogurtAlreadyBookedException("회원님은 이미 해당 강좌에 예약을 하셨습니다.");
                }
            }
        }
    }

    @Transactional
    public LectureBooking cancel(Long userId, Long lectureBookingId) {
        LectureBooking lectureBooking = this.getLectureBookingById(lectureBookingId);
        UserTicket userTicket = lectureBooking.getUserTicket();

        userTicket.validateUserOwner(userId);
        userTicket.validateRemainingCancel();

        LectureItem lectureItem = lectureBooking.getLectureItem();
        lectureItem.validateExceedLectureCancelTime();

        userTicket.canceled();
        userTicketService.save(userTicket);

        lectureBooking.canceled();
        return lectureBookingRepository.save(lectureBooking);
    }

    private LectureBooking getLectureBookingById(Long id) {
        return lectureBookingRepository.findById(id).orElseThrow(() -> new YogurtDataNotExistsException("강의를 찾을 수 없습니다."));
    }

    @Transactional
    public boolean existsLectureItemById(Long id) {
        return lectureItemRepository.existsById(id);
    }

    @Transactional
    public boolean existsLectureBookingById(Long id) {
        return lectureBookingRepository.existsById(id);
    }
}
