package com.yogurt.domain.auth.domain;

import com.yogurt.domain.base.entity.BaseEntity;
import com.yogurt.domain.base.model.Email;
import com.yogurt.domain.base.model.VerificationType;
import lombok.*;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.Entity;

@Getter
@Setter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class Verification extends BaseEntity {

    @AttributeOverrides({
            @AttributeOverride( name = "email", column = @Column(nullable = false)),
    })
    private Email email;

    @Column(nullable = false)
    private String verificationCode;

    private VerificationType type;

    public static Verification of(String email, String verificationCode, String type) {
        return builder()
                .email(Email.of(email))
                .verificationCode(verificationCode)
                .type(VerificationType.of(type))
                .build();
    }

    public String getEmail() {
        return email.getEmail();
    }

    public String getType() {
        return type.getType();
    }
}
