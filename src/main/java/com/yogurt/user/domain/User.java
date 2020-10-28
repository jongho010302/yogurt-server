package com.yogurt.user.domain;

import com.yogurt.generic.base.BaseEntity;
import com.yogurt.generic.user.domain.Date;
import com.yogurt.generic.user.domain.Email;
import com.yogurt.generic.user.domain.Gender;
import com.yogurt.generic.user.domain.Phone;
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
public class User extends BaseEntity {

    @Column(nullable = false)
    private Long studioId;

    private Email email;

    @Column(nullable = false, unique = true)
    private String username;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String name;

    private Gender gender;

    @AttributeOverrides({
            @AttributeOverride( name = "date", column = @Column(name = "birthday", nullable = false, length = 10)),
    })
    private Date birthday;

    private Phone phone;

    @Column(nullable = false)
    private String profileUrl;

    @Column(nullable = false)
    private String role;

    @Column(nullable = false)
    private Boolean isExit;

    @Column
    private String exitReason;

    public String getGender() {
        return gender.getGender();
    }

    public String getBirthday() {
        return birthday.getDate();
    }

    public String getPhone() {
        return phone.getPhone();
    }

    public String getEmail() {
        return email.getEmail();
    }

}
