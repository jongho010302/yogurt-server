package com.yogurt.domain.lecture.service.member;

import com.yogurt.domain.lecture.domain.LectureBooking;
import com.yogurt.domain.lecture.domain.LectureItem;
import com.yogurt.domain.lecture.infra.member.MemberLectureBookingRepository;
import com.yogurt.domain.lecture.infra.member.MemberLectureItemRepository;
import com.yogurt.domain.ticket.domain.UserTicket;
import com.yogurt.domain.ticket.service.admin.AdminUserTicketService;
import com.yogurt.domain.user.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MemberLectureServiceImpl implements MemberLectureService {

    private final MemberUserTicketService userTicketService;

    private final MemberLectureItemRepository memberLectureItemRepository;

    private final MemberLectureBookingRepository memberLectureBookingRepository;

    @Transactional
    public List<LectureItem> getAllWithFilter(Long studioId, Pageable pageable, String startAt, String endAt, String weekDay, Long staffId, String classType) {
        return memberLectureItemRepository.getAllWithFilter(pageable, studioId, startAt, endAt, weekDay, staffId, classType);
    }

    @Transactional
    public List<LectureBooking> getBookingList(User user, Long userTicketId) {
        List<UserTicket> userTickets = userTicketService.getAllByUser(user);
        List<LectureBooking> lectureBookings = memberLectureBookingRepository.findByUserTicketInAndIsCanceled(userTickets, false);
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
        UserTicket savedUserTicket = userTicketService.create(userTicket);

        LectureBooking lectureBooking = LectureBooking.builder()
                .userTicket(savedUserTicket)
                .lectureItem(lectureItem)
                .isCanceled(false)
                .isAttended(false)
                .build();
        return memberLectureBookingRepository.save(lectureBooking);
    }

    private LectureItem getLectureItemById(Long id) {
        return memberLectureItemRepository.findById(id).orElseThrow(() -> new YogurtDataNotExistsException("강의를 찾을 수 없습니다."));
    }

    private void validateBooking(Long userId, LectureItem lectureItem) {
        List<LectureBooking> lectureBookingList = memberLectureBookingRepository.findByLectureItem(lectureItem);
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
        return memberLectureBookingRepository.save(lectureBooking);
    }

    private LectureBooking getLectureBookingById(Long id) {
        return memberLectureBookingRepository.findById(id).orElseThrow(() -> new YogurtDataNotExistsException("강의를 찾을 수 없습니다."));
    }
}
