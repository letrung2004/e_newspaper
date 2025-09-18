package com.newspaper.notificationservice.service;


import com.newspaper.notificationservice.dto.request.EmailRequest;
import com.newspaper.notificationservice.dto.request.SendEmailRequest;
import com.newspaper.notificationservice.dto.request.Sender;
import com.newspaper.notificationservice.dto.response.EmailResponse;
import com.newspaper.notificationservice.exception.AppException;
import com.newspaper.notificationservice.exception.ErrorCode;
import com.newspaper.notificationservice.repository.httpclient.EmailClient;
import feign.FeignException;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class EmailService {
    EmailClient emailClient;
    String apiKey;

    public EmailService(EmailClient emailClient,
                        @Value("${brevo.api-key}") String apiKey) {
        this.emailClient = emailClient;
        this.apiKey = apiKey;
    }

    public EmailResponse setEmail(SendEmailRequest request) {
        EmailRequest emailRequest = EmailRequest.builder()
                .sender(Sender.builder()
                        .name("E-Newspaper")
                        .email("lequoctrunggg@gmail.com")
                        .build())
                .to(List.of(request.getTo()))
                .subject(request.getSubject())
                .htmlContent(request.getHtmlContent())
                .build();
        try {
            return emailClient.sendEmail(apiKey, emailRequest);
        } catch (FeignException e) {
            log.error("Error sending email", e);
            throw new AppException(ErrorCode.CANNOT_SEND_EMAIL);
        }
    }
}
