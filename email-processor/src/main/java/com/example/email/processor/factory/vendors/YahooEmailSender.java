package com.example.email.processor.factory.vendors;

import com.example.email.processor.factory.AbstractEmailSender;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

@Component
public class YahooEmailSender extends AbstractEmailSender {
    public YahooEmailSender(@Qualifier("yahooMailSender") JavaMailSender mailSender) {
        super(mailSender, "Yahoo");
    }
}