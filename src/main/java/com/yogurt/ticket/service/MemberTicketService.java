package com.yogurt.ticket.service;

import com.yogurt.member.dto.SaveMemberTicketRequest;
import com.yogurt.ticket.domain.MemberTicket;
import com.yogurt.user.domain.User;

import java.util.List;

public interface MemberTicketService {

    MemberTicket getById(Long id);

    List<MemberTicket> getAllByUser(User user);

    MemberTicket save(MemberTicket memberTicket);

    MemberTicket saveMemberTicket(SaveMemberTicketRequest saveMemberTicketRequest);

    void deactivateById(Long memberTicketId);

    boolean existsById(Long id);
}
