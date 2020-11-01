package com.yogurt.staff.domain;

import com.yogurt.generic.base.BaseEntity;
import com.yogurt.generic.user.domain.Date;
import com.yogurt.user.domain.User;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class Staff extends BaseEntity {

    @OneToOne(cascade = {CascadeType.ALL})
    private User user;

    @Builder.Default
    @OneToMany(cascade = {CascadeType.ALL})
    private List<StaffSchedule> schedules = new ArrayList<>();

    @Column(columnDefinition = "TEXT")
    private String selfIntroduce;

    @AttributeOverrides({
            @AttributeOverride( name = "date", column = @Column(name = "hired_at", nullable = false, length = 10)),
    })
    private Date hiredAt;

    @Column(nullable = false)
    private Boolean isDisabled;

    public String getHiredAt() {
        return hiredAt.getDate();
    }
}
