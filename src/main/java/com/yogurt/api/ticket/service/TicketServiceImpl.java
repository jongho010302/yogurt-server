package com.yogurt.api.ticket.service;

import com.yogurt.base.exception.YogurtAlreadyDataExistsException;
import com.yogurt.base.exception.YogurtDataNotExistsException;
import com.yogurt.api.ticket.domain.Ticket;
import com.yogurt.api.ticket.infra.TicketRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TicketServiceImpl implements TicketService {

    private final TicketRepository repository;

    @Transactional
    public Ticket getById(Long id) {
        return repository.findById(id).orElseThrow(() -> new YogurtDataNotExistsException("존재하지 않는 수강권입니다."));
    }

    @Transactional
    public List<Ticket> getAllWithFilter(Boolean isSelling, String classType, Pageable pageable) {
        return repository.getAllWithFilter(isSelling, classType, pageable);
    }

    @Transactional
    public Ticket save(Ticket ticket) {
        return repository.save(ticket);
    }

    @Transactional
    public void delete(Long id) {
        Ticket ticket = this.getById(id);
        if (ticket.getIsDeleted()) {
            throw new YogurtAlreadyDataExistsException("이미 사용 중지된 수강권입니다.");
        }
        ticket.deleted();
        repository.save(ticket);
    }

    @Transactional
    public boolean existsById(Long id) {
        return repository.existsById(id);
    }
}
