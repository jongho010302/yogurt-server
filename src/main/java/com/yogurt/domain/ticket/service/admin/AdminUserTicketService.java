package com.yogurt.domain.ticket.service.admin;

import com.yogurt.domain.ticket.domain.UserTicket;
import com.yogurt.domain.ticket.dto.admin.SaveUserTicketRequest;

public interface AdminUserTicketService {

    UserTicket getById(Long id);

    UserTicket create(UserTicket userTicket);

    void deleteById(Long id);

    boolean existsById(Long id);

    UserTicket saveUserTicket(SaveUserTicketRequest saveUserTicketRequest);
}
