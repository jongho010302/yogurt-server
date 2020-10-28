package com.yogurt.ticket.dto;

import com.yogurt.validation.annotation.TicketValid;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
public class DeleteTicketRequest {

    @TicketValid
    @NotNull(message = "수강권은 필수 값입니다.")
    private Long ticketId;

}
