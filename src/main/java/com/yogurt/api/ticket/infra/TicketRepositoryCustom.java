package com.yogurt.api.ticket.infra;

import com.yogurt.api.ticket.domain.Ticket;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface TicketRepositoryCustom {

    List<Ticket> getAllWithFilter(Boolean isSelling, String classType, Pageable pageable);
}
