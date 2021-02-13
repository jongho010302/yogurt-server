package com.yogurt.domain.ticket.service.admin;

import com.yogurt.domain.ticket.domain.Ticket;
import com.yogurt.domain.ticket.infra.admin.AdminTicketRepository;
import com.yogurt.domain.ticket.service.common.CommonTicketService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AdminTicketServiceImpl implements AdminTicketService {

    private final CommonTicketService commonTicketService;

    private final AdminTicketRepository repository;

    @Transactional
    public Ticket getById(Long id) {
        return commonTicketService.getById(id);
    }

    @Transactional
    public Ticket create(Ticket ticket) {
        return commonTicketService.create(ticket);
    }

    @Transactional
    public void deleteById(Long id) {
        commonTicketService.deleteById(id);
    }

    @Transactional
    public boolean existsById(Long id) {
        return commonTicketService.existsById(id);
    }

    @Transactional
    public List<Ticket> getAllWithFilter(Boolean isSelling, String classType, Pageable pageable) {
        return repository.getAllWithFilter(isSelling, classType, pageable);
    }
}
