package com.yogurt.studio.domain;

import com.yogurt.generic.base.BaseEntity;
import com.yogurt.generic.user.domain.Date;
import com.yogurt.generic.user.domain.TelNo;
import lombok.*;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.Entity;

@Getter
@Setter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class Studio extends BaseEntity {

    // 센터 이름
    @Column(nullable = false)
    private String name;

    // 센터 전화번호 (xxxx-xxxx-xxxx)
    private TelNo telNo;

    // 센터 주소1
    @Column(nullable = false)
    private String addr1;

    // 센터 주소2
    @Column(nullable = false)
    private String addr2;

    // 센터 등록일
    @AttributeOverrides({
            @AttributeOverride( name = "date", column = @Column(name = "reg_at", nullable = false, length = 10)),
    })
    private Date regAt;

    public String getTelNo() {
        return telNo.getTelNo();
    }

    public String getRegAt() {
        return regAt.getDate();
    }

}
