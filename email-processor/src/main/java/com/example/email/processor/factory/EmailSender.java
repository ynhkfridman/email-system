package com.example.email.processor.factory;

import com.example.email.model.EmailMessage;


public interface EmailSender {
    void sendEmail(EmailMessage message);
}

