package com.example.email.processor.factory;

import com.example.email.processor.config.EmailProperties;
import com.example.email.processor.factory.vendors.GmailEmailSender;
import com.example.email.processor.factory.vendors.WallaEmailSender;
import com.example.email.processor.factory.vendors.YahooEmailSender;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class EmailSenderFactory {
    private static final Logger logger = LoggerFactory.getLogger(EmailSenderFactory.class);
    
    private final Map<String, EmailSender> senderMap;
    private final EmailSender defaultSender;

    public EmailSenderFactory(
            GmailEmailSender gmail, 
            YahooEmailSender yahoo, 
            WallaEmailSender walla,
            EmailProperties gmailProperties,
            EmailProperties yahooProperties,
            EmailProperties wallaProperties) {
        
        Map<String, EmailSender> map = new HashMap<>();
        map.put(gmailProperties.getPostfix(), gmail);
        map.put(yahooProperties.getPostfix(), yahoo);
        map.put(wallaProperties.getPostfix(), walla);
        
        this.senderMap = Map.copyOf(map);
        this.defaultSender = gmail; // Use Gmail as default
        
        logger.info("Email sender factory initialized with {} providers", senderMap.size());
    }

    public EmailSender getSender(String from) {
        return senderMap.entrySet().stream()
            .filter(entry -> from.endsWith(entry.getKey()))
            .map(Map.Entry::getValue)
            .findFirst()
            .orElseGet(() -> {
                logger.warn("No specific sender found for domain in '{}', using default sender", from);
                return defaultSender;
            });
    }
}