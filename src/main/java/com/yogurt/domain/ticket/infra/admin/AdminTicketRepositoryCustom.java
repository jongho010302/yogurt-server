package com.yogurt.domain.ticket.infra.admin;

import com.yogurt.domain.ticket.domain.Ticket;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface AdminTicketRepositoryCustom {

    List<Ticket> getAllWithFilter(Boolean isSelling, String classType, Pageable pageable);
}
