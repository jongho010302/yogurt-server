package com.yogurt.user.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.yogurt.generic.base.BaseEntity;
import com.yogurt.generic.user.domain.Date;
import com.yogurt.generic.user.domain.Email;
import com.yogurt.generic.user.domain.Gender;
import com.yogurt.generic.user.domain.Phone;
import lombok.*;

import javax.persistence.*;

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

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @JsonIgnore
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

    @Transient
    private String displayRole;

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

    public String getDisplayRole() {
        String displayRole;
        if (role.equals("ROLE_DEVELOPER")) {
            displayRole = "스튜디오 개발자";
        } else if (role.equals("ROLE_OWNER")) {
            displayRole = "스튜디오 오너";
        } else if (role.equals("ROLE_MANAGER")) {
            displayRole = "매니저";
        } else if (role.equals("ROLE_INSTRUCTOR")) {
            displayRole = "강사";
        } else if (role.equals("ROLE_MEMBER")) {
            displayRole = "회원";
        } else {
            displayRole = "UNKNOWN ROLE";
        }
        return displayRole;
    }

}
