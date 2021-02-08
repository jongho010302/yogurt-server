package com.yogurt.api.user.service;

import com.yogurt.api.user.domain.User;
import com.yogurt.api.user.dto.common.ChangeEmailRequest;
import com.yogurt.api.user.dto.common.CheckResponse;
import com.yogurt.generic.user.domain.UserRole;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public interface UserService {

    User getById(Long id);

    CheckResponse checkUser(User user, long studioId);

    User changeName(Long id, String name);

    User changePhone(Long id, String phone);

    User changeProfile(Long id, MultipartFile multipartFile);

    User changeEmail(Long id, ChangeEmailRequest changeEmailRequest);

    User changeRole(Long id, UserRole.RoleEnum role);

    User getByEmail(String Email);

    List<User> getAllWithFilter(Pageable pageable, Boolean isDeleted);

    boolean existsByEmail(String email);

    User changePassword(Long userId, String rawPassword);

    boolean existsById(Long id);
}
