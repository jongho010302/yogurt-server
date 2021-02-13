package com.yogurt.domain.auth.dto.oauth;

import com.yogurt.domain.base.model.Email;
import com.yogurt.domain.base.model.Phone;
import com.yogurt.domain.base.model.UserRole;
import com.yogurt.domain.user.domain.AuthType;
import com.yogurt.domain.user.domain.User;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GoogleOAuthResponse {
    private String id;
    private String email;
    private boolean verified_email;
    private String name;
    private String given_name;
    private String family_name;
    private String picture;
    private String phone; // nullable
    private String local;

    public User toEntity() {
        return User.builder()
                .email(Email.of(email))
                .authType(AuthType.Google)
                .name(name)
                .phone(Phone.of(phone))
                .profileUrl(picture)
                .role(UserRole.RoleEnum.ROLE_MEMBER)
                .build();
    }
}
