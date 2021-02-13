package com.yogurt.domain.user.service.admin;

import com.yogurt.domain.base.model.UserRole;
import com.yogurt.domain.ticket.domain.UserTicket;
import com.yogurt.domain.ticket.service.admin.AdminTicketService;
import com.yogurt.domain.ticket.service.admin.AdminUserTicketService;
import com.yogurt.domain.ticket.service.common.CommonTicketService;
import com.yogurt.domain.ticket.service.common.CommonUserTicketService;
import com.yogurt.domain.user.domain.User;
import com.yogurt.domain.user.dto.admin.response.UsersResponse;
import com.yogurt.domain.user.infra.admin.AdminUserRepository;
import com.yogurt.domain.user.service.common.CommonUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AdminUserServiceImpl implements AdminUserService {

    private final CommonUserService commonUserService;

    private final AdminUserRepository repository;

    private final CommonTicketService commonTicketService;

    private final AdminUserTicketService adminUserTicketService;

    @Transactional
    public User getById(Long id) {
        return commonUserService.getById(id);
    }

    @Transactional
    public List<UsersResponse> getAllWithFilter(Pageable pageable) {
        List<UsersResponse> usersResponse = repository.getAllWithFilter(pageable);
        usersResponse.stream()
                .forEach(el -> {
                    List<UserTicket> userTicket = adminUserTicketService.getAllByUser(el.getId());
                    el.addUserTickets(userTicket);
                });

        return usersResponse;
    }

    @Transactional
    public User changeRole(Long id, UserRole.RoleEnum role) {
        User user = this.getById(id);
        user.setRole(role);
        return repository.save(user);
    }
}
