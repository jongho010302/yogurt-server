package com.yogurt.domain.ticket.service.common;

import com.yogurt.domain.ticket.domain.Ticket;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface CommonTicketService {

    Ticket getById(Long id);

    Ticket create(Ticket ticket);

    void deleteById(Long ticketId);

    boolean existsById(Long id);
}
