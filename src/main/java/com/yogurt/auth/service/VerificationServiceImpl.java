package com.yogurt.auth.service;

import com.yogurt.auth.domain.Verification;
import com.yogurt.auth.domain.VerificationRepository;
import com.yogurt.auth.dto.SendVerificationCodeRequest;
import com.yogurt.base.exception.YogurtDataNotExistsException;
import com.yogurt.base.exception.YogurtDifferentVerificationCodeException;
import com.yogurt.base.exception.YogurtVerifyTimeoutException;
import com.yogurt.base.util.StringUtils;
import com.yogurt.generic.user.domain.Email;
import com.yogurt.generic.user.domain.VerificationType;
import com.yogurt.mail.service.MailService;
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

    private final VerificationRepository repository;

    private final MailService mailService;

    @Transactional
    public void sendVerificationCode(SendVerificationCodeRequest sendVerificationCodeRequest) {
        String verificationCode = StringUtils.getUUID(5);
        String verificationType = sendVerificationCodeRequest.getVerificationType();
        String email = sendVerificationCodeRequest.getEmail();

        repository.save(Verification.of(email, verificationCode, verificationType));

        Map<String, Object> mailContext = new HashMap<>();
        mailContext.put("verificationCode", verificationCode);

        if (verificationType.equals(VerificationType.VERIFICATION_TYPE.SIGNUP.name())) {
            mailService.send("/signup-user-verify", mailContext, "Anonymous User", email);
        } else if (verificationType.equals(VerificationType.VERIFICATION_TYPE.FIND_PASSWORD.name())) {
            mailService.send("/find-password-verify", mailContext, "Anonymous User", email);
        } else if (verificationType.equals(VerificationType.VERIFICATION_TYPE.CHANGE_EMAIL.name())) {
            mailService.send("/change-email-verify", mailContext, "Anonymous User", email);
        }
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
