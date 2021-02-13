package com.yogurt.domain.user.service.base;

import com.yogurt.domain.auth.domain.AuthContext;
import com.yogurt.domain.user.domain.User;
import com.yogurt.domain.user.dto.common.ChangeEmailRequest;
import com.yogurt.domain.user.dto.common.CheckResponse;
import com.yogurt.generic.user.domain.UserRole;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface BaseUserService {

    CheckResponse checkUser(AuthContext authContext);

    User changeName(Long id, String name);

    User changePhone(Long id, String phone);

    User changeProfile(Long id, MultipartFile multipartFile);

    User changeEmail(Long id, ChangeEmailRequest changeEmailRequest);

    User changePassword(Long userId, String rawPassword);
}
