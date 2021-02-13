package com.yogurt.domain.ticket.service.member;

import com.yogurt.domain.ticket.domain.UserTicket;
import com.yogurt.domain.user.domain.User;

import java.util.List;

public interface MemberUserTicketService {

    UserTicket getById(Long id);

    UserTicket create(UserTicket userTicket);

    List<UserTicket> getAllByUser(User user);
}
