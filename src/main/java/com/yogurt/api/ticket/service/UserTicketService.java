package com.yogurt.api.ticket.service;

import com.yogurt.api.ticket.domain.UserTicket;
import com.yogurt.api.ticket.dto.SaveUserTicketRequest;
import com.yogurt.api.user.domain.User;

import java.util.List;

public interface UserTicketService {

    UserTicket getById(Long id);

    List<UserTicket> getAllByUser(User user);

    UserTicket save(UserTicket userTicket);

    UserTicket saveUserTicket(SaveUserTicketRequest saveUserTicketRequest);

    void deactivateById(Long memberTicketId);

    boolean existsById(Long id);
}
