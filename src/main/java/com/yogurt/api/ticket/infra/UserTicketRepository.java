package com.yogurt.api.ticket.infra;

import com.yogurt.api.ticket.domain.Ticket;
import com.yogurt.api.ticket.domain.UserTicket;
import com.yogurt.api.user.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;
import java.util.List;

public interface UserTicketRepository extends JpaRepository<UserTicket, Long> {
    List<UserTicket> findByUserAndIsDeletedAndEndDateGreaterThanEqual(User user, Boolean isDeleted, Date date);
}
