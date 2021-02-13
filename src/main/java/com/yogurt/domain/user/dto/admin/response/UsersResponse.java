package com.yogurt.domain.user.dto.admin.response;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.yogurt.domain.base.model.Email;
import com.yogurt.domain.base.model.Phone;
import com.yogurt.domain.base.model.UserRole;
import com.yogurt.domain.user.domain.AuthType;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UsersResponse {

    private Email email;

    private AuthType authType;

    private String name;

    private Phone phone;

    private String profileUrl;

    @JsonIgnore
    private UserRole.RoleEnum role;

    private String ticketTitle;

    private String maxCoupon;

    private String remainingCoupon;

    private String startDate;

    private String endDate;

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

    public String getPhone() {
        return phone.getPhone();
    }

    public String getEmail() {
        return email.getEmail();
    }

    public UsersResponse(Email email, AuthType authType, String name, Phone phone, String profileUrl, UserRole.RoleEnum role,
        String title, String maxCoupon, String remainingCoupon, String remainingCoupon, String startDate, String endDate) {
        this.email = email;
        this.authType = authType;
        this.name = name;
        this.phone = phone;
        this.profileUrl = profileUrl;
        this.role = role;
    }
}
