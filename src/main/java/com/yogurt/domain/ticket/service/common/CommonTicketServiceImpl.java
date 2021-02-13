package com.yogurt.domain.ticket.service.common;

import com.yogurt.domain.ticket.domain.Ticket;
import com.yogurt.domain.ticket.exception.TicketNotFoundException;
import com.yogurt.domain.ticket.infra.common.CommonAdminTicketRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@RequiredArgsConstructor
public class CommonTicketServiceImpl implements CommonTicketService {

    private final CommonAdminTicketRepository repository;

    @Transactional
    public Ticket getById(Long id) {
        return repository.findById(id).orElseThrow(() -> new TicketNotFoundException(id));
    }

    @Transactional
    public Ticket create(Ticket ticket) {
        return repository.save(ticket);
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
