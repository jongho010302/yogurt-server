package com.yogurt.api.ticket.service;

import com.yogurt.base.exception.YogurtAlreadyDataExistsException;
import com.yogurt.base.exception.YogurtDataNotExistsException;
import com.yogurt.api.ticket.domain.Ticket;
import com.yogurt.api.ticket.domain.UserTicket;
import com.yogurt.api.ticket.dto.SaveUserTicketRequest;
import com.yogurt.api.ticket.infra.UserTicketRepository;
import com.yogurt.api.user.domain.User;
import com.yogurt.api.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserTicketServiceImpl implements UserTicketService {

    private final UserService userService;

    private final TicketService ticketService;

    private final UserTicketRepository repository;

    @Transactional
    public UserTicket getById(Long id) {
        return repository.findById(id).orElseThrow(() -> new YogurtDataNotExistsException("존재하지 않는 회원 수강권입니다."));
    }

    @Transactional
    public List<UserTicket> getAllByUser(User user) {
        return repository.findByUser(user);
    }

    @Transactional
    public UserTicket save(UserTicket userTicket) {
        return repository.save(userTicket);
    }

    @Transactional
    public UserTicket saveUserTicket(SaveUserTicketRequest saveUserTicketRequest) {
        User user = userService.getById(saveUserTicketRequest.getUserId());
        Ticket ticket = ticketService.getById(saveUserTicketRequest.getTicketId());

        UserTicket userTicket = saveUserTicketRequest.toEntity(user, ticket);
        return repository.save(userTicket);
    }

    @Transactional
    public void deactivateById(Long id) {
        UserTicket userTicket = this.getById(id);
        if (userTicket.getIsDeactivated()) {
            throw new YogurtAlreadyDataExistsException("이미 중지된 회원 수강권입니다.");
        }
        userTicket.setIsDeactivated(true);
        repository.save(userTicket);
    }

    @Transactional
    public boolean existsById(Long id) {
        return repository.existsById(id);
    }
}
