package com.yogurt.api.ticket.service;

import com.yogurt.api.ticket.domain.Ticket;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface TicketService {

    Ticket getById(Long id);

    List<Ticket> getAllWithFilter(Boolean isSelling, String classType, Pageable pageable);

    Ticket save(Ticket ticket);

    void deactivateById(Long ticketId);

    boolean existsById(Long id);
}
