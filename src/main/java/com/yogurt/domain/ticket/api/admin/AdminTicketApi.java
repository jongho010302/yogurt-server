package com.yogurt.domain.ticket.api.admin;

import com.yogurt.domain.ticket.domain.Ticket;
import com.yogurt.domain.ticket.dto.admin.SaveTicketRequest;
import com.yogurt.domain.ticket.service.admin.AdminTicketService;
import com.yogurt.global.common.response.ApiResponse;
import com.yogurt.global.common.response.Meta;
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
public class AdminTicketApi {

    private final AdminTicketService service;

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
        Ticket ticket = service.create(saveTicketRequest.toEntity());
        return new ResponseEntity<>(ApiResponse.createSuccessApiResponse("수강권이 저장되었습니다.", ticket), HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<ApiResponse> deactivate(@PathVariable Long id) {
        service.deleteById(id);
        return new ResponseEntity<>(ApiResponse.createSuccessApiResponse("수강권이 삭제되었습니다."), HttpStatus.OK);
    }
}
