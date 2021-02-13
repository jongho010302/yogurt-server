package com.yogurt.domain.ticket.service.member;

import com.yogurt.domain.ticket.domain.UserTicket;
import com.yogurt.domain.ticket.dto.admin.SaveUserTicketRequest;
import com.yogurt.domain.user.domain.User;

import java.util.List;

public interface AdminUserTicketService {

    UserTicket getById(Long id);

    List<UserTicket> getAllByUser(User user);

    UserTicket save(UserTicket userTicket);

    UserTicket saveUserTicket(SaveUserTicketRequest saveUserTicketRequest);

    void delete(Long id);

    boolean existsById(Long id);
}
