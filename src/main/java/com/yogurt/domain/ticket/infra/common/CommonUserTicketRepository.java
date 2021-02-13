package com.yogurt.domain.ticket.infra.common;

import com.yogurt.domain.ticket.domain.UserTicket;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommonUserTicketRepository extends JpaRepository<UserTicket, Long> {
}
