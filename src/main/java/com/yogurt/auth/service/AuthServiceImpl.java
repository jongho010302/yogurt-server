package com.yogurt.auth.service;

import com.yogurt.auth.dto.FindPasswordRequest;
import com.yogurt.auth.dto.LoginRequest;
import com.yogurt.auth.dto.LoginResponse;
import com.yogurt.auth.dto.SaveMemberRequest;
import com.yogurt.base.crypto.CryptoService;
import com.yogurt.base.exception.YogurtAlreadyDataUseException;
import com.yogurt.base.exception.YogurtDataNotExistsException;
import com.yogurt.base.exception.YogurtNoAuthException;
import com.yogurt.base.exception.YogurtWrongPasswordException;
import com.yogurt.base.security.JwtTokenProvider;
import com.yogurt.base.util.StringUtils;
import com.yogurt.generic.user.domain.VerificationType;
import com.yogurt.mail.service.MailService;
import com.yogurt.member.domain.Member;
import com.yogurt.member.service.MemberService;
import com.yogurt.studio.domain.Studio;
import com.yogurt.studio.service.StudioService;
import com.yogurt.user.domain.User;
import com.yogurt.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class AuthServiceImpl implements AuthService {

    private final UserService userService;

    private final MemberService memberService;

    private final MailService mailService;

    private final VerificationService verificationService;

    private final StudioService studioService;

    private final CryptoService cryptoService;

    private final JwtTokenProvider jwtTokenProvider;

    @Transactional
    public List<Studio> getStudioList() {
        return studioService.getAll();
    }

    @Transactional
    public LoginResponse login(LoginRequest loginRequest) {
        User user = userService.getByUsername(loginRequest.getUsername());

        boolean isSamePassword = cryptoService.compare(loginRequest.getPassword(), user.getPassword());
        if (!isSamePassword) {
            throw new YogurtWrongPasswordException("잘못된 비밀번호입니다.");
        }

        if (user.getIsExit()) {
            throw new YogurtNoAuthException("탈퇴된 회원입니다.");
        }

        String jwtToken = jwtTokenProvider.createToken(user.getUsername(), user.getRole());
        return LoginResponse.of(jwtToken, user);
    }

    @Transactional
    public Member saveMember(SaveMemberRequest saveMemberRequest) {
        boolean existsUsername = userService.existsByUsername(saveMemberRequest.getUsername());
        if (existsUsername) {
            throw new YogurtAlreadyDataUseException("이미 사용중인 아이디입니다.");
        }

        boolean existsEmail = userService.existsByEmail(saveMemberRequest.getEmail());
        if (existsEmail) {
            throw new YogurtAlreadyDataUseException("이미 사용중인 이메일입니다.");
        }

        verificationService.verifyEmail(saveMemberRequest.getEmail(), saveMemberRequest.getVerificationCode(), VerificationType.VERIFICATION_TYPE.SIGNUP.name());

        String encryptedPassword = cryptoService.encode(saveMemberRequest.getPassword());
        Member member = saveMemberRequest.toEntity(encryptedPassword);

        saveMemberSendMail(member.getUser());

        return memberService.save(member);
    }

    private void saveMemberSendMail(User user) {
        Map<String, Object> dataMap = new HashMap<>();
        dataMap.put("name", user.getName());
        mailService.send("/signup-user", dataMap, user.getName(), user.getEmail());
    }

    @Transactional
    public void validateUsername(String username) {
        if (userService.existsByUsername(username)) {
            throw new YogurtAlreadyDataUseException("이미 사용중인 아이디입니다.");
        }
    }

    @Transactional
    public List<String> findMaskingUsername(String name) {
        List<User> userList = userService.getByName(name);
        if (userList.isEmpty()) {
            throw new YogurtDataNotExistsException("해당 이름으로 등록된 유저가 없습니다.");
        }

        return userList
                .stream()
                .map((user) ->
                        userService.getMaskingUsername(user.getUsername()))
                .collect(Collectors.toList());
    }

    @Transactional
    public void findUsername(String email) {
        User user = userService.getByEmail(email);
        findUsernameSendMail(user);
    }

    private void findUsernameSendMail(User user) {
        Map<String, Object> dataMap = new HashMap<>();
        dataMap.put("name", user.getName());
        dataMap.put("username", user.getUsername());
        mailService.send("/find-username", dataMap, user.getName(), user.getEmail());
    }

    @Transactional
    public void findPassword(FindPasswordRequest findPasswordRequest) {
        String email = findPasswordRequest.getEmail();
        String verificationCode = findPasswordRequest.getVerificationCode();
        String password = findPasswordRequest.getPassword();

        verificationService.verifyEmail(email, verificationCode, VerificationType.VERIFICATION_TYPE.FIND_PASSWORD.name());

        User user = userService.getByEmail(email);
        userService.changePassword(user.getId(), password);
    }

    public String getRandomPassword() {
        return StringUtils.getUUID(12).replaceAll("-", "") + "A!";
    }
}
