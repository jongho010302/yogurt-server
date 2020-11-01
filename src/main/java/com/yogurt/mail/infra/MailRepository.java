package com.yogurt.mail.infra;

import com.yogurt.mail.domain.Mail;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MailRepository extends JpaRepository<Mail, Long> {
}
