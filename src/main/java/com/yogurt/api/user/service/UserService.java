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

    User save(User user);

    void logout(HttpServletRequest request);

    User changeName(Long id, String name);

    User changePhone(Long id, String phone);

    User changeUsername(Long id, String username);

    User changeProfile(Long id, MultipartFile multipartFile);

    User changeEmail(Long id, ChangeEmailRequest changeEmailRequest);

    User changeRole(Long id, String role);

    User getByEmail(String Email);

    User getByUsername(String username);

    List<User> getByName(String name);

    List<User> getAllWithFilter(Pageable pageable, Boolean isExit);

    boolean existsByEmail(String email);

    boolean existsByUsername(String username);

    User exit(Long id, String exit);

    User changePassword(Long userId, String rawPassword);

    String getMaskingUsername(String username);

    boolean existsById(Long id);
}
