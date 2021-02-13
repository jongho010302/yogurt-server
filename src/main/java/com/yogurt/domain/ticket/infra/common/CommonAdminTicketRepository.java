package com.yogurt.domain.ticket.infra.common;

import com.yogurt.domain.ticket.domain.Ticket;
import com.yogurt.domain.ticket.infra.admin.AdminTicketRepositoryCustom;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommonAdminTicketRepository extends JpaRepository<Ticket, Long>, AdminTicketRepositoryCustom {
}
