package com.yogurt.domain.user.dto.admin.request;

import com.yogurt.domain.base.model.UserRole;
import com.yogurt.validation.annotation.UserRoleValid;
import com.yogurt.validation.annotation.UserValid;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

@Getter
@Setter
public class ChangeRoleRequest {

    @UserValid
    @NotNull(message = "유저는 필수 값입니다.")
    private Long userId;

    @UserRoleValid
    @NotEmpty(message = "직급은 필수 값입니다.")
    private UserRole.RoleEnum role;

    List<String> list;
}
