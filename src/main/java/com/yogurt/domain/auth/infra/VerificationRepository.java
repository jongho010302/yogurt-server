package com.yogurt.domain.auth.infra;

import com.yogurt.domain.auth.domain.Verification;
import com.yogurt.domain.base.model.Email;
import com.yogurt.domain.base.model.VerificationType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface VerificationRepository extends JpaRepository<Verification, Long> {

    Optional<Verification> findTopByEmailAndTypeOrderByCreatedAtDesc(Email email, VerificationType type);
}
