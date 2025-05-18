package com.example.email.processor.factory;

import com.example.email.model.EmailMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;

public abstract class AbstractEmailSender implements EmailSender {
    private static final Logger logger = LoggerFactory.getLogger(AbstractEmailSender.class);
    
    private final JavaMailSender mailSender;
    private final String providerName;

    protected AbstractEmailSender(JavaMailSender mailSender, String providerName) {
        this.mailSender = mailSender;
        this.providerName = providerName;
    }

    @Override
    public final void sendEmail(EmailMessage message) {
        try {
            MimeMessage mimeMessage = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true, "UTF-8");

            configureMessage(helper, message);

            mailSender.send(mimeMessage);
            logSuccess(message);
        } catch (MessagingException e) {
            handleError(message, e);
        }
    }

    protected void configureMessage(MimeMessageHelper helper, EmailMessage message) throws MessagingException {
        helper.setFrom(message.getFrom());
        helper.setTo(message.getTo());
        helper.setText(message.getBody());
    }

    protected void logSuccess(EmailMessage message) {
        logger.info("Email sent via {} to: {}", providerName, message.getTo());
    }

    protected void handleError(EmailMessage message, MessagingException e) {
        logger.error("Failed to send email via {} to {}: {}", providerName, message.getTo(), e.getMessage());
        throw new EmailSendException("Failed to send email via " + providerName, e);
    }
}