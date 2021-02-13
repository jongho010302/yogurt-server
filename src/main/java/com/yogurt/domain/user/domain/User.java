package com.yogurt.domain.user.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.yogurt.domain.base.entity.BaseEntity;
import com.yogurt.domain.base.model.Email;
import com.yogurt.domain.base.model.Phone;
import com.yogurt.domain.base.model.UserRole;
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
public class User extends BaseEntity {

    @ElementCollection
    @Builder.Default
    private List<Long> studioIds = new ArrayList<>();

    private Email email;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @JsonIgnore
    @Column
    private String password;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private AuthType authType;

    @Column(nullable = false)
    private String name;

    private Phone phone;

    @Column
    private String profileUrl;

    @Column(nullable = false)
    private UserRole.RoleEnum role;

    @Transient
    private String displayRole;

    public String getPhone() {
        return phone.getPhone();
    }

    public String getEmail() {
        return email.getEmail();
    }

    public String getDisplayRole() {
        String displayRole;
        if (role.equals(UserRole.RoleEnum.ROLE_DEVELOPER)) {
            displayRole = "스튜디오 개발자";
        } else if (role.equals(UserRole.RoleEnum.ROLE_OWNER)) {
            displayRole = "스튜디오 오너";
        } else if (role.equals(UserRole.RoleEnum.ROLE_MANAGER)) {
            displayRole = "매니저";
        } else if (role.equals(UserRole.RoleEnum.ROLE_INSTRUCTOR)) {
            displayRole = "강사";
        } else if (role.equals(UserRole.RoleEnum.ROLE_MEMBER)) {
            displayRole = "회원";
        } else {
            displayRole = "UNKNOWN ROLE";
        }
        return displayRole;
    }
}
