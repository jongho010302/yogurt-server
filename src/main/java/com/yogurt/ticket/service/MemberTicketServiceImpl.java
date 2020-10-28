package com.yogurt.ticket.service;

import com.yogurt.base.exception.YogurtAlreadyDataExistsException;
import com.yogurt.base.exception.YogurtDataNotExistsException;
import com.yogurt.member.domain.Member;
import com.yogurt.member.domain.MemberTicketRepository;
import com.yogurt.member.dto.SaveMemberTicketRequest;
import com.yogurt.member.service.MemberService;
import com.yogurt.ticket.domain.MemberTicket;
import com.yogurt.ticket.domain.Ticket;
import com.yogurt.user.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MemberTicketServiceImpl implements MemberTicketService {

    private final MemberService memberService;

    private final TicketService ticketService;

    private final MemberTicketRepository repository;

    @Transactional
    public MemberTicket getById(Long id) {
        return repository.findById(id).orElseThrow(() -> new YogurtDataNotExistsException("존재하지 않는 회원 수강권입니다."));
    }

    @Transactional
    public List<MemberTicket> getAllByUser(User user) {
        Member member = memberService.getByUser(user);
        return repository.findByMember(member);
    }

    @Transactional
    public MemberTicket save(MemberTicket memberTicket) {
        return repository.save(memberTicket);
    }

    @Transactional
    public MemberTicket saveMemberTicket(SaveMemberTicketRequest saveMemberTicketRequest) {
        Member member = memberService.getById(saveMemberTicketRequest.getMemberId());
        Ticket ticket = ticketService.getById(saveMemberTicketRequest.getTicketId());

        MemberTicket memberTicket = saveMemberTicketRequest.toEntity(member, ticket);
        return repository.save(memberTicket);
    }

    @Transactional
    public void deactivateById(Long id) {
        MemberTicket memberTicket = this.getById(id);
        if (memberTicket.getIsDeactivated()) {
            throw new YogurtAlreadyDataExistsException("이미 중지된 회원 수강권입니다.");
        }
        memberTicket.setIsDeactivated(true);
        repository.save(memberTicket);
    }

    @Transactional
    public boolean existsById(Long id) {
        return repository.existsById(id);
    }
}
