package com.yogurt.ticket.controller.admin;

import com.yogurt.base.dto.ApiResponse;
import com.yogurt.member.dto.DeleteMemberTicketRequest;
import com.yogurt.member.dto.SaveMemberTicketRequest;
import com.yogurt.ticket.domain.MemberTicket;
import com.yogurt.ticket.service.MemberTicketService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RequiredArgsConstructor
@RestController
@RequestMapping("/admin/member-tickets")
public class AdminMemberTicketController {

    private final MemberTicketService service;

    @GetMapping("{id}")
    public ResponseEntity<ApiResponse> get(@PathVariable Long id) {
        MemberTicket memberTicket = service.getById(id);
        return new ResponseEntity<>(ApiResponse.createSuccessApiResponse("회원 수강권입니다.", memberTicket), HttpStatus.OK);
    }

    @PostMapping("")
    public ResponseEntity<ApiResponse> saveMemberTicket(@RequestBody @Valid SaveMemberTicketRequest saveMemberTicketRequest) {
        MemberTicket memberTicket = service.saveMemberTicket(saveMemberTicketRequest);
        return new ResponseEntity<>(ApiResponse.createSuccessApiResponse("회원에게 수강권이 저장되었습니다.", memberTicket), HttpStatus.OK);
    }

    @DeleteMapping("")
    public ResponseEntity<ApiResponse> deleteMemberTicket(@RequestBody @Valid DeleteMemberTicketRequest deleteMemberTicketRequest) {
        service.deactivateById(deleteMemberTicketRequest.getUserTicketId());
        return new ResponseEntity<>(ApiResponse.createSuccessApiResponse("회원의 수강권이 비활성화 되었습니다."), HttpStatus.OK);
    }
}
