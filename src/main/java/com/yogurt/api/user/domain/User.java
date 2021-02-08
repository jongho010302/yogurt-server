package com.yogurt.api.user.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.yogurt.api.studio.domain.Studio;
import com.yogurt.base.exception.YogurtNoAuthException;
import com.yogurt.generic.base.BaseEntity;
import com.yogurt.generic.user.domain.Email;
import com.yogurt.generic.user.domain.Phone;
import com.yogurt.generic.user.domain.UserRole;
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

    @Builder.Default
    @OneToMany(cascade = {CascadeType.ALL})
    private List<Studio> studios = new ArrayList<>();

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

    public void validateDeletion() {
        if (this.getIsDeleted()) {
            throw new YogurtNoAuthException("탈퇴된 회원입니다.");
        }
    }
}
