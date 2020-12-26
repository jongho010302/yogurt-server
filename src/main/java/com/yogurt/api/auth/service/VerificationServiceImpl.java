package com.yogurt.api.auth.service;

import com.yogurt.api.auth.domain.Verification;
import com.yogurt.api.auth.domain.VerificationRepository;
import com.yogurt.api.auth.dto.SendVerificationCodeRequest;
import com.yogurt.api.user.infra.UserRepository;
import com.yogurt.api.user.service.UserService;
import com.yogurt.base.exception.*;
import com.yogurt.base.util.StringUtils;
import com.yogurt.generic.user.domain.Email;
import com.yogurt.generic.user.domain.VerificationType;
import com.yogurt.api.mail.service.MailService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@RequiredArgsConstructor
@Service
public class VerificationServiceImpl implements VerificationService {

    private final UserRepository userRepository;

    private final VerificationRepository repository;

    private final MailService mailService;

    @Transactional
    public void sendSignupCode(String email) {
        String verificationCode = getVerificationCode();

        repository.save(Verification.of(email, verificationCode, VerificationType.VERIFICATION_TYPE.SIGNUP.toString()));

        Map<String, Object> mailContext = new HashMap<>();
        mailContext.put("verificationCode", verificationCode);

        mailService.send("/signup-user-verify", mailContext, "Anonymous User", email);
    }

    private String getVerificationCode() {
        String verificationCode = StringUtils.getUUID(5);
        return verificationCode;
    }

    public boolean existsUserByEmail(String email) {
        return userRepository.existsByEmail(Email.of(email));
    }

    @Transactional
    public void verifySignupCode(String email, String verificationCode, String type) {
        boolean isUserExists = existsUserByEmail(email);
        if (isUserExists) {
            throw new YogurtAlreadyDataExistsException("이미 가입된 이메일입니다. 다른 이메일을 사용해주세요.");
        }

        verifyEmail(email, verificationCode, type);
    }

    @Transactional
    public void sendFindPasswordCode(String email) {
        String verificationCode = getVerificationCode();

        repository.save(Verification.of(email, verificationCode, VerificationType.VERIFICATION_TYPE.SIGNUP.toString()));

        Map<String, Object> mailContext = new HashMap<>();
        mailContext.put("verificationCode", verificationCode);

        mailService.send("/find-password-verify", mailContext, "Anonymous User", email);
    }

    @Transactional
    public void verifyFindPasswordCode(String email, String verificationCode, String type) {
        boolean isUserExists = existsUserByEmail(email);
        if (!isUserExists) {
            throw new YogurtAlreadyDataExistsException("가입되지 않은 이메일입니다. 다른 이메일을 사용해주세요.");
        }

        verifyEmail(email, verificationCode, type);
    }

    @Transactional
    public void sendChangeEmailCode(String email) {
        String verificationCode = getVerificationCode();

        repository.save(Verification.of(email, verificationCode, VerificationType.VERIFICATION_TYPE.SIGNUP.toString()));

        Map<String, Object> mailContext = new HashMap<>();
        mailContext.put("verificationCode", verificationCode);

        mailService.send("/change-email-verify", mailContext, "Anonymous User", email);
    }

    @Transactional
    public void verifyChangeEmailCode(String email, String verificationCode, String type) {
        boolean isUserExists = existsUserByEmail(email);
        if (!isUserExists) {
            throw new YogurtAlreadyDataExistsException("가입되지 않은 이메일입니다. 다른 이메일을 사용해주세요.");
        }

        verifyEmail(email, verificationCode, type);
    }

    @Transactional
    public void verifyEmail(String email, String verificationCode, String type) {
        Verification verification = repository.findTopByEmailAndTypeOrderByCreatedAtDesc(
                Email.of(email), VerificationType.of(type))
                .orElseThrow(() -> new YogurtDataNotExistsException("인증번호가 전송된 적이 없는 이메일입니다."));

        Calendar currentCal = Calendar.getInstance();
        currentCal.setTime(new Date());

        Calendar verifyLimitCal = Calendar.getInstance();
        verifyLimitCal.setTime(verification.getCreatedAt());
        verifyLimitCal.add(Calendar.DATE, 1);

        if (currentCal.after(verifyLimitCal)) {
            throw new YogurtVerifyTimeoutException("이메일 인증 시간 초과를 하였습니다.");
        }

        if (!verification.getVerificationCode().equals(verificationCode)) {
            throw new YogurtDifferentVerificationCodeException("인증번호가 다릅니다.");
        }
    }
}
