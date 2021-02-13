package com.yogurt.domain.user.dto.admin.response;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.querydsl.core.annotations.QueryProjection;
import com.yogurt.domain.base.model.Email;
import com.yogurt.domain.base.model.Phone;
import com.yogurt.domain.base.model.UserRole;
import com.yogurt.domain.ticket.domain.UserTicket;
import com.yogurt.domain.user.domain.AuthType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Getter
@Setter
public class UsersResponse {

    private Long id;

    private Email email;

    private AuthType authType;

    private String name;

    private Phone phone;

    private String profileUrl;

    @JsonIgnore
    private UserRole.RoleEnum role;

    private String createdAt;

    List<UserTicketDto> userTickets = new ArrayList<>();

    public String getRole() {
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

    @QueryProjection
    public UsersResponse(Long id, Email email, AuthType authType, String name, Phone phone, String profileUrl, UserRole.RoleEnum role, Date createdAt) {
        this.id = id;
        this.email = email;
        this.authType = authType;
        this.name = name;
        this.phone = phone;
        this.profileUrl = profileUrl;
        this.role = role;
        this.createdAt = createdAt.toString().substring(0, 10);

    }

    public void addUserTickets(List<UserTicket> userTickets) {
        userTickets.stream()
                .forEach((el) -> {
                    String ticketDate = el.getStartDate().toString().substring(0, 10) + " ~ " + el.getEndDate().toString().substring(0, 10);
                    UserTicketDto userTicketDto = new UserTicketDto(el.getTicket().getTitle(), el.getMaxCoupon(), el.getRemainingCoupon(), ticketDate);
                    this.userTickets.add(userTicketDto);
                });
    }
}

@Getter
@AllArgsConstructor
class UserTicketDto {
    private String ticketTitle;
    private int maxCoupon;
    private int remainingCoupon;
    private String ticketDate;
}
