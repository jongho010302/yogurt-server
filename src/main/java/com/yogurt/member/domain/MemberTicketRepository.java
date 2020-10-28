package com.yogurt.member.domain;

import com.yogurt.ticket.domain.MemberTicket;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface MemberTicketRepository extends JpaRepository<MemberTicket, Long> {
    List<MemberTicket> findByMember(Member member);
}
