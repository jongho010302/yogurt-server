package com.yogurt.api.user.service;

import com.yogurt.api.user.domain.User;
import com.yogurt.api.user.dto.common.ChangeEmailRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public interface UserService {

    User getById(Long id);

    User checkUser(User user);

    User changeName(Long id, String name);

    User changePhone(Long id, String phone);

    User changeProfile(Long id, MultipartFile multipartFile);

    User changeEmail(Long id, ChangeEmailRequest changeEmailRequest);

    User changeRole(Long id, String role);

    User getByEmail(String Email);

    User delete(User user, String deleteReason);

    User delete(Long id, String deleteReason);

    List<User> getAllWithFilter(Pageable pageable, Boolean isDeleted);

    boolean existsByEmail(String email);

    User changePassword(Long userId, String rawPassword);

    boolean existsById(Long id);
}
