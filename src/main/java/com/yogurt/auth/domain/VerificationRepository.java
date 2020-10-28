package com.yogurt.auth.domain;

import com.yogurt.generic.user.domain.Email;
import com.yogurt.generic.user.domain.VerificationType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface VerificationRepository extends JpaRepository<Verification, Long> {

    Optional<Verification> findTopByEmailAndTypeOrderByCreatedAtDesc(Email email, VerificationType type);
}
