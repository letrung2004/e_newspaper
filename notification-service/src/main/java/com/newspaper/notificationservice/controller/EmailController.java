package com.newspaper.notificationservice.controller;

import com.newspaper.notificationservice.dto.ApiResponse;
import com.newspaper.notificationservice.dto.request.SendEmailRequest;
import com.newspaper.notificationservice.dto.response.EmailResponse;
import com.newspaper.notificationservice.service.EmailService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class EmailController {
    EmailService emailService;

    @PostMapping("/email/send")
    public ApiResponse<EmailResponse> sendEmail(@RequestBody SendEmailRequest request) {
        return ApiResponse.<EmailResponse>builder()
                .result(emailService.setEmail(request))
                .build();
    }

//    @KafkaListener(topics = "notification-delivery")
//    public void listen(String message) {
//        try {
//            log.info("Message onboard-successful received: {}", message);
//            // Gọi emailService.setEmail hoặc xử lý chính
//        } catch (Exception e) {
//            log.error("Failed to handle message: {}", message, e);
//            // Có thể throw lại nếu muốn retry, hoặc ignore nếu muốn skip
//        }
//    }
}
