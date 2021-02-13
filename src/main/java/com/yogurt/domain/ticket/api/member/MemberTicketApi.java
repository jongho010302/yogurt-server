package com.yogurt.domain.ticket.api.member;

import com.yogurt.domain.auth.domain.AuthContext;
import com.yogurt.base.dto.ApiResponse;
import com.yogurt.domain.ticket.domain.UserTicket;
import com.yogurt.domain.ticket.service.admin.AdminUserTicketService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/member/tickets")
public class MemberTicketApi {

    private final AdminUserTicketService service;

    @GetMapping("")
    public ResponseEntity<ApiResponse> getAll(@AuthenticationPrincipal AuthContext authContext) {
        List<UserTicket> userTicketList = service.getAllByUser(authContext.getUser());
        return new ResponseEntity<>(ApiResponse.createSuccessApiResponse("회원의 수강권이 조회되었습니다.", userTicketList), HttpStatus.OK);
    }
}
