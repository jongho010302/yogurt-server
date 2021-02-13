package com.yogurt.domain.ticket.service.member;

import com.yogurt.domain.ticket.domain.Ticket;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface AdminTicketService {

    Ticket getById(Long id);

    List<Ticket> getAllWithFilter(Boolean isSelling, String classType, Pageable pageable);

    Ticket save(Ticket ticket);

    void delete(Long ticketId);

    boolean existsById(Long id);
}
