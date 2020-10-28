package com.yogurt.ticket.controller.ticket;

import com.yogurt.base.dto.ApiResponse;
import com.yogurt.ticket.domain.MemberTicket;
import com.yogurt.ticket.service.MemberTicketService;
import com.yogurt.user.domain.User;
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
public class MemberTicketController {

    private final MemberTicketService service;

    @GetMapping("")
    public ResponseEntity<ApiResponse> getAll(@AuthenticationPrincipal User user) {
        List<MemberTicket> memberTicketList = service.getAllByUser(user);
        return new ResponseEntity<>(ApiResponse.createSuccessApiResponse("회원의 수강권이 조회되었습니다.", memberTicketList), HttpStatus.OK);
    }
}
