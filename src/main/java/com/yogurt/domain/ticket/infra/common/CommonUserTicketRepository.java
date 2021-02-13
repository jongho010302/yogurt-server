package com.yogurt.domain.ticket.infra.common;

import com.yogurt.domain.ticket.domain.UserTicket;
import com.yogurt.domain.user.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;
import java.util.List;

public interface CommonUserTicketRepository extends JpaRepository<UserTicket, Long> {
}
