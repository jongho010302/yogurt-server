package com.yogurt.api.staff.service;

import com.yogurt.api.auth.service.AuthService;
import com.yogurt.api.user.infra.UserRepository;
import com.yogurt.base.crypto.CryptoService;
import com.yogurt.base.exception.YogurtAlreadyDataExistsException;
import com.yogurt.base.exception.YogurtAlreadyDataUseException;
import com.yogurt.base.exception.YogurtDataNotExistsException;
import com.yogurt.api.mail.service.MailService;
import com.yogurt.api.staff.domain.Staff;
import com.yogurt.api.staff.dto.SaveStaffRequest;
import com.yogurt.api.staff.infra.StaffRepository;
import com.yogurt.api.user.domain.User;
import com.yogurt.api.user.service.UserService;
import com.yogurt.base.util.DateUtils;
import com.yogurt.base.util.StringUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
@Service
public class StaffServiceImpl implements StaffService {

    private final UserRepository userRepository;

    private final UserService userService;

    private final CryptoService cryptoService;

    private final MailService mailService;

    private final StaffRepository staffRepository;

    @Transactional
    public Staff getById(Long id) {
        return staffRepository.findById(id).orElseThrow(() -> new YogurtDataNotExistsException("존재 하지 않는 직원입니다."));
    }

    @Transactional
    public Staff getByUser(User user) {
        return staffRepository.findByUser(user).orElseThrow(() -> new YogurtDataNotExistsException("존재 하지 않는 직원입니다."));
    }

    @Transactional
    public List<Staff> getAllWithFilter(Pageable pageable, Boolean isDeleted) {
        return staffRepository.getAllWithFilter(pageable, isDeleted);
    }

    @Transactional
    public Staff saveStaff(SaveStaffRequest saveStaffRequest) {
        if (userService.existsByEmail(saveStaffRequest.getEmail())) {
            throw new YogurtAlreadyDataUseException("이미 사용중인 이메일입니다.");
        }

        String randomPassword = StringUtils.getRandomPassword();
        String encryptedPassword = cryptoService.encode(randomPassword);
        Staff staffEntity = saveStaffRequest.toEntity(encryptedPassword);

        Staff staff = staffRepository.save(staffEntity);

        staffSignupSendMail(staff, randomPassword);

        return staff;
    }

    @Transactional
    public void delete(Long id) {
        Staff staff = this.getById(id);

        this.deleteUser(staff.getUser().getId());
        staffRepository.deleteById(id);
    }

    private void deleteUser(long userId) {
        userRepository.deleteById(userId);
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

        String randomPassword = StringUtils.getRandomPassword();
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
        return staffRepository.existsById(id);
    }
}
