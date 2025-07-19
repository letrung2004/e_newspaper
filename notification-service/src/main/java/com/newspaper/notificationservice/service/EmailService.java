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
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class EmailService {
    EmailClient emailClient;

    String apiKey = "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";

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
            throw new AppException(ErrorCode.CANNOT_SEND_EMAIL);
        }
    }
}
