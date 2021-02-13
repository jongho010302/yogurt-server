package com.yogurt.domain.ticket.service.member;

import com.yogurt.domain.ticket.domain.UserTicket;
import com.yogurt.domain.ticket.infra.member.MemberUserTicketRepository;
import com.yogurt.domain.ticket.service.common.CommonUserTicketService;
import com.yogurt.domain.user.domain.User;
import com.yogurt.util.DateUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MemberUserTicketServiceImpl implements MemberUserTicketService {

    private final CommonUserTicketService commonUserTicketService;

    private final MemberUserTicketRepository repository;

    @Transactional
    public UserTicket getById(Long id) {
        return commonUserTicketService.getById(id);
    }

    @Transactional
    public UserTicket create(UserTicket userTicket) {
        return commonUserTicketService.create(userTicket);
    }

    @Transactional
    public List<UserTicket> getAllByUser(User user) {
        Date currentDate = DateUtils.getCurrentDate();
        return repository.findByUserAndEndDateGreaterThanEqual(user, currentDate);
    }
}
