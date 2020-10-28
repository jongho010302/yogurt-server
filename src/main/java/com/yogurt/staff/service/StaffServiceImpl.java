package com.yogurt.staff.service;

import com.yogurt.auth.service.AuthService;
import com.yogurt.base.crypto.CryptoService;
import com.yogurt.base.exception.YogurtAlreadyDataExistsException;
import com.yogurt.base.exception.YogurtAlreadyDataUseException;
import com.yogurt.base.exception.YogurtDataNotExistsException;
import com.yogurt.mail.service.MailService;
import com.yogurt.staff.domain.Staff;
import com.yogurt.staff.domain.StaffRepository;
import com.yogurt.staff.dto.SaveStaffRequest;
import com.yogurt.user.domain.User;
import com.yogurt.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
@Service
public class StaffServiceImpl implements StaffService {

    private final AuthService authService;

    private final UserService userService;

    private final CryptoService cryptoService;

    private final MailService mailService;

    private final StaffRepository repository;

    @Transactional
    public Staff getById(Long id) {
        return repository.findById(id).orElseThrow(() -> new YogurtDataNotExistsException("존재 하지 않는 직원입니다."));
    }

    @Transactional
    public Staff getByUser(User user) {
        return repository.findByUser(user).orElseThrow(() -> new YogurtDataNotExistsException("존재 하지 않는 직원입니다."));
    }

    @Transactional
    public List<Staff> getAllWithFilter(Pageable pageable, Boolean isDisabled) {
        return repository.getAllWithFilter(pageable, isDisabled);
    }

    @Transactional
    public Staff saveStaff(SaveStaffRequest saveStaffRequest) {
        if (userService.existsByUsername(saveStaffRequest.getUsername())) {
            throw new YogurtAlreadyDataUseException("이미 사용중인 아이디입니다.");
        }

        if (userService.existsByEmail(saveStaffRequest.getEmail())) {
            throw new YogurtAlreadyDataUseException("이미 사용중인 이메일입니다.");
        }

        String randomPassword = authService.getRandomPassword();
        String encryptedPassword = cryptoService.encode(randomPassword);
        Staff staffEntity = saveStaffRequest.toEntity(encryptedPassword);

        Staff staff = repository.save(staffEntity);

        staffSignupSendMail(staff, randomPassword);

        return staff;
    }

    @Transactional
    public void deactivate(Long id) {
        Staff staff = this.getById(id);
        if (staff.getIsDisabled()) {
            throw new YogurtAlreadyDataExistsException("이미 비활성화된 직원입니다.");
        }
        staff.setIsDisabled(true);
        repository.save(staff);
    }

    private void staffSignupSendMail(Staff staff, String randomPassword) {
        Map<String, Object> dataMap = new HashMap<>();
        dataMap.put("name", staff.getUser().getName());
        dataMap.put("password", randomPassword);
        mailService.send("/signup-staff", dataMap, staff.getUser().getName(), staff.getUser().getEmail());
    }

    @Transactional
    public void resetPassword(long userId) {
        User user = userService.getById(userId);

        String randomPassword = authService.getRandomPassword();
        userService.changePassword(userId, randomPassword);

        resetPasswordSendMail(user, randomPassword);
    }

    private void resetPasswordSendMail(User user, String randomPassword) {
        Map<String, Object> dataMap = new HashMap<>();
        dataMap.put("name", user.getName());
        dataMap.put("password", randomPassword);
        mailService.send("/reset-password", dataMap, user.getName(), user.getEmail());
    }

    @Transactional
    public boolean existsById(Long id) {
        return repository.existsById(id);
    }
}
