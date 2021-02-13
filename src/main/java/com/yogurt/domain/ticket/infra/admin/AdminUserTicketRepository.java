package com.yogurt.domain.ticket.infra.admin;

import com.yogurt.domain.ticket.domain.UserTicket;
import com.yogurt.domain.user.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;
import java.util.List;

public interface AdminUserTicketRepository extends JpaRepository<UserTicket, Long> {
    List<UserTicket> findByUserIdAndEndDateGreaterThanEqual(long userId, Date date);
}
