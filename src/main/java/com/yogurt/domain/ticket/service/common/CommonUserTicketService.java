package com.yogurt.domain.ticket.service.common;

import com.yogurt.domain.ticket.domain.UserTicket;

public interface CommonUserTicketService {

    UserTicket getById(Long id);

    UserTicket create(UserTicket userTicket);

    void deleteById(Long id);

    boolean existsById(Long id);
}
