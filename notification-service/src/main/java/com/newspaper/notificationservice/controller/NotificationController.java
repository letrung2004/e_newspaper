package com.newspaper.notificationservice.controller;


import com.newspaper.event.dto.NotificationEvent;
import com.newspaper.notificationservice.dto.request.Recipient;
import com.newspaper.notificationservice.dto.request.SendEmailRequest;
import com.newspaper.notificationservice.service.EmailService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Slf4j
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Component
public class NotificationController {

    EmailService emailService;


    @KafkaListener(topics = "notification-delivery")
    public void listenNotificationDelivery(NotificationEvent message) {
        try {
            log.info("Message received: {}", message.getRecipient());

            emailService.setEmail(SendEmailRequest.builder()
                    .to(Recipient.builder()
                            .email(message.getRecipient())
                            .build())
                    .subject(message.getSubject())
                    .htmlContent(message.getBody())
                    .build());

            log.info("Email sent successfully to: {}", message.getRecipient());
        } catch (Exception e) {
            log.error("Failed to send email to: {}, error: {}", message.getRecipient(), e.getMessage());
            throw e;
        }
    }
}
