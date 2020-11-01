package com.yogurt.ticket.infra;

import com.yogurt.ticket.domain.UserTicket;
import com.yogurt.user.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserTicketRepository extends JpaRepository<UserTicket, Long> {
    List<UserTicket> findByUser(User user);
}
