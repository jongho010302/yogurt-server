package com.yogurt.domain.mail.infra;

import com.yogurt.domain.mail.domain.Mail;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MailRepository extends JpaRepository<Mail, Long> {
}
