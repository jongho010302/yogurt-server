package com.yogurt.domain.ticket.infra.admin;

import com.yogurt.domain.ticket.domain.UserTicket;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdminUserTicketRepository extends JpaRepository<UserTicket, Long> {
}
