package com.yogurt.domain.ticket.infra.admin;

import com.yogurt.domain.ticket.domain.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdminTicketRepository extends JpaRepository<Ticket, Long>, AdminTicketRepositoryCustom {
}
