package com.example.email.processor.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import java.util.Properties;

@Configuration
public class EmailConfig {
    private static final Logger logger = LoggerFactory.getLogger(EmailConfig.class);

    @Bean
    @ConfigurationProperties(prefix = "email.gmail")
    public EmailProperties gmailProperties() {
        return new EmailProperties();
    }

    @Bean
    @ConfigurationProperties(prefix = "email.yahoo")
    public EmailProperties yahooProperties() {
        return new EmailProperties();
    }

    @Bean
    @ConfigurationProperties(prefix = "email.walla")
    public EmailProperties wallaProperties() {
        return new EmailProperties();
    }

    @Bean
    public JavaMailSender gmailMailSender(EmailProperties gmailProperties) {
        logger.info("Configuring Gmail mail sender");
        return createMailSender(gmailProperties);
    }

    @Bean
    public JavaMailSender yahooMailSender(EmailProperties yahooProperties) {
        logger.info("Configuring Yahoo mail sender");
        return createMailSender(yahooProperties);
    }

    @Bean
    public JavaMailSender wallaMailSender(EmailProperties wallaProperties) {
        logger.info("Configuring Walla mail sender");
        return createMailSender(wallaProperties);
    }

    private JavaMailSender createMailSender(EmailProperties properties) {
        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
        mailSender.setHost(properties.getServer());
        mailSender.setPort(properties.getPort());
        mailSender.setUsername(properties.getUsername());
        mailSender.setPassword(properties.getPassword());

        Properties javaMailProperties = new Properties();
        javaMailProperties.put("mail.transport.protocol", "smtp");
        javaMailProperties.put("mail.smtp.auth", "true");
        javaMailProperties.put("mail.smtp.starttls.enable", "true");
        javaMailProperties.put("mail.debug", "false");

        mailSender.setJavaMailProperties(javaMailProperties);
        return mailSender;
    }
}