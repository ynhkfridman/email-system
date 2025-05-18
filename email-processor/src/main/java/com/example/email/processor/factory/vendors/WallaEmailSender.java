package com.example.email.processor.factory.vendors;

import com.example.email.processor.factory.AbstractEmailSender;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

@Component
public class WallaEmailSender extends AbstractEmailSender {
    public WallaEmailSender(@Qualifier("wallaMailSender") JavaMailSender mailSender) {
        super(mailSender, "Walla");
    }
}