package com.yogurt.domain.ticket.service.admin;

import com.yogurt.domain.ticket.domain.UserTicket;
import com.yogurt.domain.ticket.dto.admin.SaveUserTicketRequest;
import com.yogurt.domain.user.domain.User;

import java.util.List;

public interface AdminUserTicketService {

    UserTicket getById(Long id);

    UserTicket create(SaveUserTicketRequest saveUserTicketRequest);

    void deleteById(Long id);

    boolean existsById(Long id);

    List<UserTicket> getAllByUser(Long userId);
}
