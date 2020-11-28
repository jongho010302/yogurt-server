package com.yogurt.api.ticket.infra;

import com.yogurt.api.ticket.domain.UserTicket;
import com.yogurt.api.user.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserTicketRepository extends JpaRepository<UserTicket, Long> {
    List<UserTicket> findByUser(User user);
}
