package com.yogurt.domain.ticket.service.common;

import com.yogurt.domain.ticket.domain.UserTicket;
import com.yogurt.domain.ticket.exception.UserTicketNotFoundException;
import com.yogurt.domain.ticket.infra.common.CommonUserTicketRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@RequiredArgsConstructor
public class CommonUserTicketServiceImpl implements CommonUserTicketService {

    private final CommonUserTicketRepository repository;

    @Transactional
    public UserTicket getById(Long id) {
        return repository.findById(id).orElseThrow(() -> new UserTicketNotFoundException(id));
    }

    @Transactional
    public UserTicket create(UserTicket userTicket) {
        return repository.save(userTicket);
    }

    @Transactional
    public void deleteById(Long id) {
        repository.deleteById(id);
    }

    @Transactional
    public boolean existsById(Long id) {
        return repository.existsById(id);
    }
}
