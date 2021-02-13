package com.yogurt.domain.auth.service;

import com.yogurt.domain.auth.domain.Verification;
import com.yogurt.domain.auth.exception.*;
import com.yogurt.domain.auth.infra.VerificationRepository;
import com.yogurt.domain.base.model.Email;
import com.yogurt.domain.base.model.VerificationType;
import com.yogurt.domain.mail.service.MailService;
import com.yogurt.domain.user.service.common.CommonUserService;
import com.yogurt.util.DateUtils;
import com.yogurt.util.StringUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

@RequiredArgsConstructor
@Service
public class VerificationServiceImpl implements VerificationService {

    private final VerificationRepository repository;

    private final MailService mailService;

    private final CommonUserService commonUserService;

    /**
     * 회원가입: 코드 전송
     * @param email
     */
    @Transactional
    public void sendSignupCode(String email) {
        this.validateEmailNotExists(email);
        String verificationCode = getRandomCode();

        repository.save(Verification.of(email, verificationCode, VerificationType.VERIFICATION_TYPE.SIGNUP.toString()));

        Map<String, Object> mailContext = new HashMap<>();
        mailContext.put("verificationCode", verificationCode);

        mailService.send("/signup-user-verify", mailContext, "Anonymous User", email);
    }


    /**
     * 회원가입: 코드 검사
     * @param email
     * @param code
     */
    @Transactional
    public void verifySignupCode(String email, String code) {
        this.validateEmailNotExists(email);
        String verificationCode = getRandomCode();

        verifyEmail(email, verificationCode, VerificationType.VERIFICATION_TYPE.SIGNUP.toString());
    }

    /**
     * 비밀번호 찾기: 코드 전송
     * @param email
     */
    @Transactional
    public void sendFindPasswordCode(String email) {
        this.validateEmailExists(email);
        String verificationCode = getRandomCode();

        repository.save(Verification.of(email, verificationCode, VerificationType.VERIFICATION_TYPE.FIND_PASSWORD.toString()));

        Map<String, Object> mailContext = new HashMap<>();
        mailContext.put("verificationCode", verificationCode);

        mailService.send("/find-password-verify", mailContext, "Anonymous User", email);
    }

    /**
     * 비밀번호 찾기: 코드 인증
     * @param email
     * @param verificationCode
     */
    @Transactional
    public void verifyFindPasswordCode(String email, String verificationCode) {
        this.validateEmailExists(email);
        verifyEmail(email, verificationCode, VerificationType.VERIFICATION_TYPE.FIND_PASSWORD.toString());
    }

    /**
     * 이메일 변경: 코드 전송
     * @param email
     */
    @Transactional
    public void sendChangeEmailCode(String email) {
        this.validateEmailNotExists(email);
        String verificationCode = getRandomCode();

        repository.save(Verification.of(email, verificationCode, VerificationType.VERIFICATION_TYPE.CHANGE_EMAIL.toString()));

        Map<String, Object> mailContext = new HashMap<>();
        mailContext.put("verificationCode", verificationCode);

        mailService.send("/change-email-verify", mailContext, "Anonymous User", email);
    }

    /**
     * 이메일 변경: 코드 인증
     * @param email
     * @param code
     */
    @Transactional
    public void verifyChangeEmailCode(String email, String code) {
        this.validateEmailNotExists(email);
        verifyEmail(email, code, VerificationType.VERIFICATION_TYPE.CHANGE_EMAIL.toString());
    }

    /**
     * 인증 공통 함수
     * @param email
     * @param verificationCode
     * @param type
     */
    @Transactional
    public void verifyEmail(String email, String verificationCode, String type) {
        Verification verification = repository.findTopByEmailAndTypeOrderByCreatedAtDesc(
                Email.of(email), VerificationType.of(type))
                .orElseThrow(() -> new CodeNotSentException());

        Calendar currentCal = Calendar.getInstance();
        currentCal.setTime(DateUtils.getCurrentDate());

        Calendar verifyLimitCal = Calendar.getInstance();
        verifyLimitCal.setTime(verification.getCreatedAt());
        verifyLimitCal.add(Calendar.DATE, 1);

        if (currentCal.after(verifyLimitCal)) {
            throw new CodeTimeoutException();
        }

        if (!verification.getVerificationCode().equals(verificationCode)) {
            throw new CodeDifferentException();
        }
    }

    private void validateEmailExists(String email) {
        boolean isUserExists = existsUserByEmail(email);
        if (!isUserExists) {
            throw new EmailNotFoundException(email);
        }
    }

    private void validateEmailNotExists(String email) {
        boolean isUserExists = existsUserByEmail(email);
        if (isUserExists) {
            throw new EmailAlreadyRegisteredException();
        }
    }

    private boolean existsUserByEmail(String email) {
        return commonUserService.existsByEmail(email);
    }

    private String getRandomCode() {
        String verificationCode = StringUtils.getUUID(5);
        return verificationCode;
    }

}
