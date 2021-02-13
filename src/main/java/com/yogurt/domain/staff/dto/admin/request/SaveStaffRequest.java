package com.yogurt.domain.staff.dto.admin.request;

import com.yogurt.domain.user.domain.AuthType;
import com.yogurt.generic.user.domain.Date;
import com.yogurt.generic.user.domain.Email;
import com.yogurt.generic.user.domain.Phone;
import com.yogurt.domain.staff.domain.Staff;
import com.yogurt.domain.staff.domain.StaffSchedule;
import com.yogurt.domain.user.domain.User;
import com.yogurt.generic.user.domain.UserRole;
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

    @EmailValid
    @NotEmpty(message = "이메일은 필수 값입니다.")
    private String email;

    @NotEmpty(message = "이름은 필수 값입니다.")
    private String name;

    @DateValid(message = "생일의 형식을 맞춰 주세요.")
    @NotEmpty(message = "생일은 필수 값입니다.")
    private String birthday;

    @PhoneValid
    @NotEmpty(message = "핸드폰 번호는 필수 값입니다.")
    private String phone;

    private String profileUrl;

    @UserRoleValid
    @NotEmpty(message = "직무는 필수 값입니다.")
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
                .email(Email.of(email))
                .authType(AuthType.Email)
                .password(password)
                .name(name)
                .phone(Phone.of(phone))
                .profileUrl(profileUrl)
                .role(UserRole.RoleEnum.valueOf(role))
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
                .build();
    }
}
