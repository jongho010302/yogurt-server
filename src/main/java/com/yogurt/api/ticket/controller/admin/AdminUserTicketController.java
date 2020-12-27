package com.yogurt.api.ticket.controller.admin;

import com.yogurt.base.dto.ApiResponse;
import com.yogurt.api.ticket.domain.UserTicket;
import com.yogurt.api.ticket.dto.SaveUserTicketRequest;
import com.yogurt.api.ticket.service.UserTicketService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RequiredArgsConstructor
@RestController
@RequestMapping("/admin/user/tickets")
public class AdminUserTicketController {

    private final UserTicketService service;

    @GetMapping("{id}")
    public ResponseEntity<ApiResponse> get(@PathVariable Long id) {
        UserTicket userTicket = service.getById(id);
        return new ResponseEntity<>(ApiResponse.createSuccessApiResponse("회원 수강권입니다.", userTicket), HttpStatus.OK);
    }

    @PostMapping("")
    public ResponseEntity<ApiResponse> saveUserTicket(@RequestBody @Valid SaveUserTicketRequest saveUserTicketRequest) {
        UserTicket userTicket = service.saveUserTicket(saveUserTicketRequest);
        return new ResponseEntity<>(ApiResponse.createSuccessApiResponse("회원에게 수강권이 저장되었습니다.", userTicket), HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<ApiResponse> deleteUserTicket(@PathVariable Long id) {
        service.delete(id);
        return new ResponseEntity<>(ApiResponse.createSuccessApiResponse("회원 수강권이 삭제되었습니다."), HttpStatus.OK);
    }
}
