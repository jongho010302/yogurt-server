package com.yogurt.domain.ticket.infra.member;

import com.yogurt.domain.ticket.domain.Ticket;
import com.yogurt.domain.ticket.domain.UserTicket;
import com.yogurt.domain.user.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;
import java.util.List;

public interface MemberTicketRepository extends JpaRepository<Ticket, Long> {
}
