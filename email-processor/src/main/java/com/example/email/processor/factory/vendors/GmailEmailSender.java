package com.example.email.processor.factory.vendors;

import com.example.email.processor.factory.AbstractEmailSender;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

@Component
public class GmailEmailSender extends AbstractEmailSender {
    public GmailEmailSender(@Qualifier("gmailMailSender") JavaMailSender mailSender) {
        super(mailSender, "Gmail");
    }
}