package com.yogurt.member.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
public class DeleteMemberTicketRequest {

    @NotNull(message = "사용자의 수강권은 필수 값입니다.")
    private Long userTicketId;
}
