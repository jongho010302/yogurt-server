package com.yogurt.domain.staff.service.admin;

import com.yogurt.domain.auth.exception.EmailAlreadyRegisteredException;
import com.yogurt.domain.mail.service.MailService;
import com.yogurt.domain.staff.domain.Staff;
import com.yogurt.domain.staff.dto.admin.request.SaveStaffRequest;
import com.yogurt.domain.staff.infra.admin.AdminStaffRepository;
import com.yogurt.domain.staff.service.common.CommonStaffService;
import com.yogurt.domain.user.domain.User;
import com.yogurt.domain.user.service.base.BaseUserService;
import com.yogurt.domain.user.service.common.CommonUserService;
import com.yogurt.global.crypto.CryptoService;
import com.yogurt.util.StringUtils;
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

    private final BaseUserService baseUserService;

    private final CommonStaffService commonStaffService;

    private final CommonUserService commonUserService;

    private final CryptoService cryptoService;

    private final MailService mailService;

    private final AdminStaffRepository adminStaffRepository;

    @Transactional
    public Staff getById(Long id) {
        return commonStaffService.getById(id);
    }

    @Transactional
    public List<Staff> getAllWithFilter(Pageable pageable) {
        return adminStaffRepository.getAllWithFilter(pageable);
    }

    @Transactional
    public Staff saveStaff(SaveStaffRequest saveStaffRequest) {
        if (commonUserService.existsByEmail(saveStaffRequest.getEmail())) {
            throw new EmailAlreadyRegisteredException();
        }

        String randomPassword = StringUtils.getRandomPassword();
        String encryptedPassword = cryptoService.encode(randomPassword);
        Staff staffEntity = saveStaffRequest.toEntity(encryptedPassword);

        Staff staff = adminStaffRepository.save(staffEntity);

        staffSignupSendMail(staff, randomPassword);

        return staff;
    }

    private void staffSignupSendMail(Staff staff, String randomPassword) {
        Map<String, Object> dataMap = new HashMap<>();
        dataMap.put("name", staff.getUser().getName());
        dataMap.put("password", randomPassword);
        mailService.send("/signup-staff", dataMap, staff.getUser().getName(), staff.getUser().getEmail());
    }

    @Transactional
    public void deleteById(Long id) {
        commonStaffService.deleteById(id);
    }

    @Transactional
    public void resetPassword(long userId) {
        User user = commonUserService.getById(userId);

        String randomPassword = StringUtils.getRandomPassword();
        baseUserService.changePassword(userId, randomPassword);

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
        return adminStaffRepository.existsById(id);
    }
}
