package com.yogurt.domain.ticket.service.admin;

import com.yogurt.domain.ticket.domain.Ticket;
import com.yogurt.domain.ticket.domain.UserTicket;
import com.yogurt.domain.ticket.dto.admin.SaveUserTicketRequest;
import com.yogurt.domain.ticket.infra.admin.AdminUserTicketRepository;
import com.yogurt.domain.ticket.service.common.CommonUserTicketService;
import com.yogurt.domain.user.domain.User;
import com.yogurt.domain.user.service.common.CommonUserService;
import com.yogurt.util.DateUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AdminUserTicketServiceImpl implements AdminUserTicketService {

    private final CommonUserTicketService commonUserTicketService;

    private final AdminTicketService adminTicketService;

    private final AdminUserTicketRepository repository;

    private final CommonUserService commonUserService;

    @Transactional
    public UserTicket getById(Long id) {
        return commonUserTicketService.getById(id);
    }

    @Transactional
    public UserTicket create(SaveUserTicketRequest saveUserTicketRequest) {
        User user = commonUserService.getById(saveUserTicketRequest.getUserId());
        Ticket ticket = adminTicketService.getById(saveUserTicketRequest.getTicketId());

        UserTicket userTicket = saveUserTicketRequest.toEntity(user, ticket);
        return repository.save(userTicket);
    }

    @Transactional
    public void deleteById(Long id) {
        commonUserTicketService.deleteById(id);
    }

    @Transactional
    public boolean existsById(Long id) {
        return commonUserTicketService.existsById(id);
    }

    @Transactional
    public List<UserTicket> getAllByUser(Long userId) {
        Date currentDate = DateUtils.getCurrentDate();
        return repository.findByUserIdAndEndDateGreaterThanEqual(userId, currentDate);
    }
}
