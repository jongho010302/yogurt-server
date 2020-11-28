package com.yogurt.api.auth.dto;

import com.yogurt.generic.user.domain.Date;
import com.yogurt.generic.user.domain.Email;
import com.yogurt.generic.user.domain.Gender;
import com.yogurt.generic.user.domain.Phone;
import com.yogurt.api.user.domain.User;
import com.yogurt.validation.annotation.*;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Getter
@Setter
public class SaveUserRequest {

    @StudioValid
    @NotNull(message = "센터는 필수 값입니다.")
    private Long studioId;

    @NotEmpty(message = "아이디는 필수 값입니다.")
    private String username;

    @PasswordValid
    @NotEmpty(message = "비밀번호는 필수 값입니다.")
    private String password;

    @EmailValid
    @NotEmpty(message = "이메일은 필수 값입니다.")
    private String email;

    @NotEmpty(message = "이름은 필수 값입니다.")
    private String name;

    @GenderValid
    @NotEmpty(message = "성별은 필수 값입니다.")
    private String gender;

    @DateValid(message = "생일의 형식을 맞춰 주세요.")
    @NotEmpty(message = "생일은 필수 값입니다.")
    private String birthday;

    @PhoneValid
    @NotEmpty(message = "핸드폰 번호는 필수 값입니다.")
    private String phone;

    @NotEmpty(message = "프로필은 필수 값입니다.")
    private String profileUrl;

    @NotEmpty(message = "인증번호는 필수 값입니다.")
    private String verificationCode;

    public User toEntity(String password) {
        return User.builder()
                .studioId(studioId)
                .username(username)
                .password(password)
                .email(Email.of(email))
                .name(name)
                .gender(Gender.of(gender))
                .birthday(Date.of(birthday))
                .phone(Phone.of(phone))
                .profileUrl(profileUrl)
                .role("ROLE_MEMBER")
                .isExit(false)
                .build();
    }

}
