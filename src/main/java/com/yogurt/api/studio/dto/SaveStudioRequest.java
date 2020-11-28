package com.yogurt.api.studio.dto;

import com.yogurt.generic.user.domain.Date;
import com.yogurt.generic.user.domain.TelNo;
import com.yogurt.api.studio.domain.Studio;
import com.yogurt.validation.annotation.DateValid;
import com.yogurt.validation.annotation.TelNoValid;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;

@Getter
@Setter
public class SaveStudioRequest {

    @NotEmpty(message = "이름은 필수 값입니다.")
    private String name;

    @TelNoValid
    @NotEmpty(message = "전화번호는 필수 값입니다.")
    private String telNo;

    @NotEmpty(message = "주소1는 필수 값입니다.")
    private String addr1;

    @NotEmpty(message = "주소2는 필수 값입니다.")
    private String addr2;

    @DateValid(message = "등록 일자의 형식을 맞춰주세요.")
    @NotEmpty(message = "등록 일자는 필수 값입니다.")
    private String regAt;

    public Studio toEntity() {
        return Studio.builder()
                .name(name)
                .telNo(TelNo.of(telNo))
                .addr1(addr1)
                .addr2(addr2)
                .regAt(Date.of(regAt))
                .build();
    }

}
