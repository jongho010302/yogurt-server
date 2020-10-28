package com.yogurt.lecture.service;

import com.yogurt.base.exception.YogurtAlreadyBookedException;
import com.yogurt.base.exception.YogurtBookingCancelTimeExceedException;
import com.yogurt.base.exception.YogurtBookingEntryExceedException;
import com.yogurt.base.exception.YogurtDataNotExistsException;
import com.yogurt.base.util.DateUtils;
import com.yogurt.lecture.domain.*;
import com.yogurt.lecture.dto.SaveLecturesRequest;
import com.yogurt.staff.domain.Staff;
import com.yogurt.staff.service.StaffService;
import com.yogurt.ticket.domain.MemberTicket;
import com.yogurt.ticket.service.MemberTicketService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
public class LectureServiceImpl implements LectureService {

    private final StaffService staffService;

    private final MemberTicketService memberTicketService;

    private final LectureRepository lectureRepository;

    private final LectureItemRepository lectureItemRepository;

    private final LectureBookingRepository lectureBookingRepository;

    @Transactional
    public List<LectureItem> getAllWithFilter(Long studioId, Pageable pageable, String startAt, String endAt, String weekDay, Long staffId, String classType) {
        return lectureItemRepository.getAllWithFilter(pageable, studioId, startAt, endAt, weekDay, staffId, classType);
    }

    @Transactional
    public List<LectureBooking> getLectureBookingList(Long userId, Long memberTicketId) {
        MemberTicket memberTicket = memberTicketService.getById(memberTicketId);
        memberTicket.validateUserOwner(userId);

        return lectureBookingRepository.findByMemberTicket(memberTicket);
    }

    @Transactional
    public Lecture save(SaveLecturesRequest saveLecturesRequest) {
        Lecture lecture = saveLecturesRequest.toLectureEntity();
        Staff staff = staffService.getById(saveLecturesRequest.getStaffId());

        boolean hasSunClass = saveLecturesRequest.getHasSunClass();
        String sunClassStartTime = saveLecturesRequest.getSunClassStartTime();
        String sunClassEndTime = saveLecturesRequest.getSunClassEndTime();
        boolean hasMonClass = saveLecturesRequest.getHasMonClass();
        String monClassStartTime = saveLecturesRequest.getMonClassStartTime();
        String monClassEndTime = saveLecturesRequest.getMonClassEndTime();
        boolean hasTueClass = saveLecturesRequest.getHasTueClass();
        String tueClassStartTime = saveLecturesRequest.getTueClassStartTime();
        String tueClassEndTime = saveLecturesRequest.getTueClassEndTime();
        boolean hasWedClass = saveLecturesRequest.getHasWedClass();
        String wedClassStartTime = saveLecturesRequest.getWedClassStartTime();
        String wedClassEndTime = saveLecturesRequest.getWedClassEndTime();
        boolean hasThuClass = saveLecturesRequest.getHasThuClass();
        String thuClassStartTime = saveLecturesRequest.getThuClassStartTime();
        String thuClassEndTime = saveLecturesRequest.getThuClassEndTime();
        boolean hasFriClass = saveLecturesRequest.getHasFriClass();
        String friClassStartTime = saveLecturesRequest.getFriClassStartTime();
        String friClassEndTime = saveLecturesRequest.getFriClassEndTime();
        boolean hasSatClass = saveLecturesRequest.getHasSatClass();
        String satClassStartTime = saveLecturesRequest.getSatClassStartTime();
        String satClassEndTime = saveLecturesRequest.getSatClassEndTime();

        String bookingEndTime = saveLecturesRequest.getBookingEndTime();
        String bookingCancelEndTime = saveLecturesRequest.getBookingCancelEndTime();
        String bookingChangeEndTime = saveLecturesRequest.getBookingChangeEndTime();

        Date startDate = DateUtils.parse(saveLecturesRequest.getStartDate());
        Date endDate = DateUtils.parse(saveLecturesRequest.getEndDate());
        Date currentDate = startDate;

        while (currentDate.compareTo(endDate) <= 0) {
            LectureItem lectureItem = saveLecturesRequest.toLectureItemEntity(lecture, staff);

            Calendar cal = Calendar.getInstance();
            cal.setTime(currentDate);

            String classStartTime = "";
            String classEndTime = "";

            int dayNum = cal.get(Calendar.DAY_OF_WEEK);
            switch (dayNum) {
                case 1:
                    if (hasSunClass) {
                        classStartTime = sunClassStartTime;
                        classEndTime = sunClassEndTime;
                    }
                    break;
                case 2:
                    if (hasMonClass) {
                        classStartTime = monClassStartTime;
                        classEndTime = monClassEndTime;
                    }
                    break;
                case 3:
                    if (hasTueClass) {
                        classStartTime = tueClassStartTime;
                        classEndTime = tueClassEndTime;
                    }
                    break;
                case 4:
                    if (hasWedClass) {
                        classStartTime = wedClassStartTime;
                        classEndTime = wedClassEndTime;
                    }
                    break;
                case 5:
                    if (hasThuClass) {
                        classStartTime = thuClassStartTime;
                        classEndTime = thuClassEndTime;
                    }
                    break;
                case 6:
                    if (hasFriClass) {
                        classStartTime = friClassStartTime;
                        classEndTime = friClassEndTime;
                    }
                    break;
                case 7:
                    if (hasSatClass) {
                        classStartTime = satClassStartTime;
                        classEndTime = satClassEndTime;
                    }
                    break;
            }

            if (classStartTime.equals("")) {
                cal.setTime(currentDate);
                cal.add(Calendar.DAY_OF_MONTH, 1);
                currentDate = cal.getTime();
                continue;
            }

            // 수업 시작 시간
            cal.set(Calendar.HOUR, Integer.parseInt(classStartTime.substring(0, 2)));
            cal.set(Calendar.MINUTE, Integer.parseInt(classStartTime.substring(3, 5)));
            lectureItem.setStartAt(DateUtils.format(cal.getTime(), "yyyy-MM-dd HH:mm:ss"));

            // 수업 종료 시간
            cal.set(Calendar.HOUR, Integer.parseInt(classEndTime.substring(0, 2)));
            cal.set(Calendar.MINUTE, Integer.parseInt(classEndTime.substring(3, 5)));
            lectureItem.setEndAt(DateUtils.format(cal.getTime(), "yyyy-MM-dd HH:mm:ss"));

            // 예약 종료 시간
            // 초기화
            cal.set(Calendar.HOUR, Integer.parseInt(classStartTime.substring(0, 2)));
            cal.set(Calendar.MINUTE, Integer.parseInt(classStartTime.substring(3, 5)));
            cal.add(Calendar.HOUR, -Integer.parseInt(bookingEndTime.substring(0, 2)));
            cal.add(Calendar.MINUTE, -Integer.parseInt(bookingEndTime.substring(3, 5)));
            lectureItem.setBookingEndAt(DateUtils.format(cal.getTime(), "yyyy-MM-dd HH:mm:ss"));

            // 예약 취소 종료 시간
            // 초기화
            cal.set(Calendar.HOUR, Integer.parseInt(classStartTime.substring(0, 2)));
            cal.set(Calendar.MINUTE, Integer.parseInt(classStartTime.substring(3, 5)));
            cal.add(Calendar.HOUR, -Integer.parseInt(bookingCancelEndTime.substring(0, 2)));
            cal.add(Calendar.MINUTE, -Integer.parseInt(bookingCancelEndTime.substring(3, 5)));
            lectureItem.setBookingCancelEndAt(DateUtils.format(cal.getTime(), "yyyy-MM-dd HH:mm:ss"));

            // 예약 변경 종료 시간
            // 초기화
            cal.set(Calendar.HOUR, Integer.parseInt(classStartTime.substring(0, 2)));
            cal.set(Calendar.MINUTE, Integer.parseInt(classStartTime.substring(3, 5)));
            cal.add(Calendar.HOUR, -Integer.parseInt(bookingChangeEndTime.substring(0, 2)));
            cal.add(Calendar.MINUTE, -Integer.parseInt(bookingChangeEndTime.substring(3, 5)));
            lectureItem.setBookingChangeEndAt(DateUtils.format(cal.getTime(), "yyyy-MM-dd HH:mm:ss"));

            lecture.getLectureItems().add(lectureItem);

            cal.setTime(currentDate);
            cal.add(Calendar.DAY_OF_MONTH, 1);
            currentDate = cal.getTime();
        }
        return lectureRepository.save(lecture);
    }

    @Transactional
    public LectureBooking book(Long userId, Long lectureItemId, Long memberTicketId) {
        MemberTicket memberTicket = memberTicketService.getById(memberTicketId);
        memberTicket.validateUserOwner(userId);
        memberTicket.validateRemainingCoupon();

        LectureItem lectureItem = this.getLectureItemById(lectureItemId);
        lectureItem.validateExceedLectureBookingTime();

        this.validateBooking(userId, lectureItem);

        memberTicket.booked();
        MemberTicket savedMemberTicket = memberTicketService.save(memberTicket);

        LectureBooking lectureBooking = LectureBooking.builder()
                .memberTicket(savedMemberTicket)
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
        this.validateMemberAlreadyBooked(userId, lectureBookingList);
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

    private void validateMemberAlreadyBooked(Long userId, List<LectureBooking> lectureBookingList) {
        for (LectureBooking lectureBooking : lectureBookingList) {
            if (!lectureBooking.getIsCanceled()) {
                boolean isMemberBooked = lectureBooking.getMemberTicket().getMember().getUser().getId().equals(userId);
                if (isMemberBooked) {
                    throw new YogurtAlreadyBookedException("회원님은 이미 해당 강좌에 예약을 하셨습니다.");
                }
            }
        }
    }

    @Transactional
    public LectureBooking cancel(Long userId, Long lectureBookingId) {
        LectureBooking lectureBooking = this.getLectureBookingById(lectureBookingId);
        MemberTicket memberTicket = lectureBooking.getMemberTicket();

        memberTicket.validateUserOwner(userId);
        memberTicket.validateRemainingCancel();

        LectureItem lectureItem = lectureBooking.getLectureItem();
        lectureItem.validateExceedLectureCancelTime();

        memberTicket.canceled();
        memberTicketService.save(memberTicket);

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
