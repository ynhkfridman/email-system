package com.example.email.gateway;

import jakarta.validation.Valid;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.email.model.EmailMessage;

import jakarta.annotation.PostConstruct;

@RestController
@RequestMapping("/email")
public class EmailController {

    private final RabbitTemplate rabbitTemplate;

    public EmailController(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    @Value("${queue.name}")
    private String queueName;

    @PostConstruct
    public void logMessageConverter() {
        System.out.println("MessageConverter in use: " + rabbitTemplate.getMessageConverter().getClass().getName());
    }

    @PostMapping
    public ResponseEntity<String> sendEmail(@Valid @RequestBody EmailMessage request) {
        rabbitTemplate.convertAndSend(queueName, request);
        return ResponseEntity.ok("Email request sent to queue");
    }
}
