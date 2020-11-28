package com.yogurt.api.ticket.controller.admin;

import com.yogurt.base.dto.ApiResponse;
import com.yogurt.base.dto.Meta;
import com.yogurt.api.ticket.domain.Ticket;
import com.yogurt.api.ticket.dto.SaveTicketRequest;
import com.yogurt.api.ticket.service.TicketService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/admin/tickets")
public class AdminTicketController {

    private final TicketService service;

    @GetMapping("")
    public ResponseEntity<ApiResponse> getAll(@RequestParam(required = false) Boolean isSelling,
                                              @RequestParam(required = false) String classType,
                                              Pageable pageable) {
        List<Ticket> ticketList = service.getAllWithFilter(isSelling, classType, pageable);
        return new ResponseEntity<>(ApiResponse.createSuccessApiResponse("수강권 리스트입니다.", ticketList, Meta.of(pageable, ticketList.size())), HttpStatus.OK);
    }

    @GetMapping("{id}")
    public ResponseEntity<ApiResponse> get(@PathVariable Long id) {
        Ticket ticket = service.getById(id);
        return new ResponseEntity<>(ApiResponse.createSuccessApiResponse("수강권입니다.", ticket), HttpStatus.OK);
    }

    @PostMapping("")
    public ResponseEntity<ApiResponse> save(@RequestBody @Valid SaveTicketRequest saveTicketRequest) {
        Ticket ticket = service.save(saveTicketRequest.toEntity());
        return new ResponseEntity<>(ApiResponse.createSuccessApiResponse("수강권이 저장되었습니다.", ticket), HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<ApiResponse> deactivate(@PathVariable Long id) {
        service.deactivateById(id);
        return new ResponseEntity<>(ApiResponse.createSuccessApiResponse("수강권이 삭제되었습니다."), HttpStatus.OK);
    }
}
