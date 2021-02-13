package com.yogurt.domain.auth.dto.oauth;

import com.yogurt.domain.base.model.Email;
import com.yogurt.domain.base.model.UserRole;
import com.yogurt.domain.user.domain.AuthType;
import com.yogurt.domain.user.domain.User;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FacebookOAuthResponse {
    private String id;
    private String email;
    private String name;

    public User toEntity() {
        return User.builder()
                .email(Email.of(email))
                .authType(AuthType.Google)
                .name(name)
                .role(UserRole.RoleEnum.ROLE_MEMBER)
                .build();
    }
}
