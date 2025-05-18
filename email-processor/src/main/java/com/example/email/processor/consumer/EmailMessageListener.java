package com.example.email.processor.consumer;

import com.example.email.model.EmailMessage;
import com.example.email.processor.factory.EmailSender;
import com.example.email.processor.factory.EmailSenderFactory;
import com.example.email.processor.logger.EmailLogger;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class EmailMessageListener {
    private static final Logger logger = LoggerFactory.getLogger(EmailMessageListener.class);

    private final EmailSenderFactory factory;
    private final EmailLogger emailLogger;

    public EmailMessageListener(EmailSenderFactory factory, EmailLogger emailLogger) {
        this.factory = factory;
        this.emailLogger = emailLogger;
    }

    @RabbitListener(queues = "${queue.name}", concurrency = "3-10")
    public void receiveEmail(EmailMessage message) {
        logger.info("Received email message to: {}", message.getTo());
        
        try {
            EmailSender sender = factory.getSender(message.getFrom());
            sender.sendEmail(message);
            emailLogger.logEmail(message);
            logger.info("Email sent successfully to: {}", message.getTo());
        } catch (Exception e) {
            logger.error("Failed to process email to {}: {}", message.getTo(), e.getMessage());
            // The message will be sent to the dead letter queue automatically
            throw e; // Re-throw to trigger the dead letter mechanism
        }
    }
}