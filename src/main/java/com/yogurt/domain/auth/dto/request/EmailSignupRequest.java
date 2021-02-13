package com.yogurt.domain.auth.dto.request;

import com.yogurt.domain.base.model.Email;
import com.yogurt.domain.base.model.Phone;
import com.yogurt.domain.base.model.UserRole;
import com.yogurt.domain.user.domain.AuthType;
import com.yogurt.domain.user.domain.User;
import com.yogurt.validation.annotation.EmailValid;
import com.yogurt.validation.annotation.PasswordValid;
import com.yogurt.validation.annotation.PhoneValid;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;

@Getter
@Setter
public class EmailSignupRequest {

    @PasswordValid
    @NotEmpty(message = "비밀번호는 필수 값입니다.")
    private String password;

    @EmailValid
    @NotEmpty(message = "이메일은 필수 값입니다.")
    private String email;

    @NotEmpty(message = "이름은 필수 값입니다.")
    private String name;

    @PhoneValid
    @NotEmpty(message = "핸드폰 번호는 필수 값입니다.")
    private String phone;

    private String profileUrl;

    @NotEmpty(message = "인증번호는 필수 값입니다.")
    private String verificationCode;

    public User toEntity(String password) {
        return User.builder()
                .password(password)
                .email(Email.of(email))
                .authType(AuthType.Email)
                .name(name)
                .phone(Phone.of(phone))
                .profileUrl(profileUrl)
                .role(UserRole.RoleEnum.ROLE_MEMBER)
                .build();
    }

}
