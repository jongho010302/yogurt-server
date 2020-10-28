package com.yogurt.ticket.domain;

import org.springframework.data.jpa.repository.JpaRepository;

public interface TicketRepository extends JpaRepository<Ticket, Long>, TicketRepositoryCustom {
}
