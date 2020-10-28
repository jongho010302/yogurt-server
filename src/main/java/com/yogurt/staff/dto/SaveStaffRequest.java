package com.yogurt.staff.dto;

import com.yogurt.generic.user.domain.Date;
import com.yogurt.generic.user.domain.Email;
import com.yogurt.generic.user.domain.Gender;
import com.yogurt.generic.user.domain.Phone;
import com.yogurt.staff.domain.Staff;
import com.yogurt.staff.domain.StaffSchedule;
import com.yogurt.user.domain.User;
import com.yogurt.validation.annotation.*;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class SaveStaffRequest {

    @StudioValid
    @NotNull(message = "센터는 필수 값입니다.")
    private Long studioId;

    @NotEmpty(message = "아이디는 필수 값입니다.")
    private String username;

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

    @UserRoleValid
    @NotEmpty(message = "직급은 필수 값입니다.")
    private String role;

    @NotEmpty(message = "자기소개는 필수 값입니다.")
    private String selfIntroduce;

    @DateValid(message = "등록일자의 형식을 맞춰 주세요.")
    @NotEmpty(message = "등록일자는 필수 값입니다.")
    private String hiredAt;

    @NotNull(message = "근무일자는 필수 값입니다.")
    private List<SaveStaffScheduleRequest> schedules;

    public Staff toEntity(String password) {
        User user = User.builder()
                .studioId(studioId)
                .email(Email.of(email))
                .username(username)
                .password(password)
                .name(name)
                .gender(Gender.of(gender))
                .name(name)
                .phone(Phone.of(phone))
                .birthday(Date.of(birthday))
                .profileUrl(profileUrl)
                .role(role)
                .isExit(false)
                .build();

        List<StaffSchedule> staffScheduleList = new ArrayList<>();
        schedules.stream().forEach(el -> {
                StaffSchedule staffSchedule = StaffSchedule.builder()
                    .startTime(el.getStartTime())
                    .endTime(el.getEndTime())
                    .datOfWeek(el.getDatOfWeek())
                    .build();
                staffScheduleList.add(staffSchedule);
        });

        return Staff.builder()
                .user(user)
                .selfIntroduce(selfIntroduce)
                .hiredAt(Date.of(hiredAt))
                .schedules(staffScheduleList)
                .isDisabled(true)
                .build();
    }
}
