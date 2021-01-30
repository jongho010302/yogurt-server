package com.yogurt;

import com.yogurt.api.studio.domain.Studio;
import com.yogurt.api.studio.infra.StudioRepository;
import com.yogurt.api.user.domain.AuthType;
import com.yogurt.api.user.domain.User;
import com.yogurt.api.user.infra.UserRepository;
import com.yogurt.base.util.DateUtils;
import com.yogurt.generic.user.domain.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.annotation.PostConstruct;

@Slf4j
@RequiredArgsConstructor
@SpringBootApplication
public class YogurtApplication {

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    private final StudioRepository studioRepository;

    public static void main(String[] args) {
        SpringApplication.run(YogurtApplication.class, args);
    }

    @PostConstruct
    public void init() {
        if (!studioRepository.findById(1L).isPresent()) {
            Studio studio = Studio.builder()
                    .name("지은 스튜디오")
                    .addr1("경기도 용인시 수지구")
                    .addr2("랜드마크 203호")
                    .regAt(Date.of("2021-01-01"))
                    .telNo(TelNo.of("031-123-1234"))
                    .build();
            studioRepository.save(studio);
            log.info("Yogurt: Studio was created.");
        }

        if (!userRepository.findById(1L).isPresent()) {
            User user = User.builder()
                    .email(Email.of("jongho.dev@gmail.com"))
                    .password(passwordEncoder.encode("Wldms0302!"))
                    .name("jongho")
                    .role(UserRole.RoleEnum.ROLE_DEVELOPER)
                    .authType(AuthType.Email)
                    .phone(Phone.of("010-7570-3529"))
                    .build();
            userRepository.save(user);
            log.info("Yogurt: User was created.");
        }

    }
}
