package com.yogurt.domain.ticket.service.admin;

import com.yogurt.domain.ticket.domain.Ticket;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface AdminTicketService {

    Ticket getById(Long id);

    Ticket create(Ticket ticket);

    void deleteById(Long ticketId);

    boolean existsById(Long id);

    List<Ticket> getAllWithFilter(Boolean isSelling, String classType, Pageable pageable);
}
