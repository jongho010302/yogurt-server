package com.yogurt.domain.ticket.service.member;

import com.yogurt.base.exception.YogurtAlreadyDataExistsException;
import com.yogurt.base.exception.YogurtDataNotExistsException;
import com.yogurt.base.util.DateUtils;
import com.yogurt.domain.ticket.domain.Ticket;
import com.yogurt.domain.ticket.domain.UserTicket;
import com.yogurt.domain.ticket.dto.admin.SaveUserTicketRequest;
import com.yogurt.domain.ticket.infra.admin.AdminUserTicketRepository;
import com.yogurt.domain.user.domain.User;
import com.yogurt.domain.user.service.common.CommonUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AdminUserTicketServiceImpl implements AdminUserTicketService {

    private final CommonUserService commonUserService;

    private final AdminTicketService adminTicketService;

    private final AdminUserTicketRepository repository;

    @Transactional
    public UserTicket getById(Long id) {
        return repository.findById(id).orElseThrow(() -> new YogurtDataNotExistsException("존재하지 않는 회원 수강권입니다."));
    }

    @Transactional
    public List<UserTicket> getAllByUser(User user) {
        Date currentDate = DateUtils.getCurrentDate();
        return repository.findByUserAndIsDeletedAndEndDateGreaterThanEqual(user, false, currentDate);
    }

    @Transactional
    public UserTicket save(UserTicket userTicket) {
        return repository.save(userTicket);
    }

    @Transactional
    public UserTicket saveUserTicket(SaveUserTicketRequest saveUserTicketRequest) {
        User user = commonUserService.getById(saveUserTicketRequest.getUserId());
        Ticket ticket = adminTicketService.getById(saveUserTicketRequest.getTicketId());

        UserTicket userTicket = saveUserTicketRequest.toEntity(user, ticket);
        return repository.save(userTicket);
    }

    @Transactional
    public void delete(Long id) {
        UserTicket userTicket = this.getById(id);
        if (userTicket.getIsDeleted()) {
            throw new YogurtAlreadyDataExistsException("이미 삭제된 회원 수강권입니다.");
        }
        userTicket.deleted();
        repository.save(userTicket);
    }

    @Transactional
    public boolean existsById(Long id) {
        return repository.existsById(id);
    }
}
