package com.newspaper.commentservice.service;

import org.springframework.stereotype.Component;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;

@Component
public class DateTimeFormatter {

    public String format(Instant instant) {
        long seconds = ChronoUnit.SECONDS.between(instant, Instant.now());
        if (seconds < 60) {
            return seconds + " giây";
        }
        if (seconds < 3600) {
            long minutes = ChronoUnit.MINUTES.between(instant, Instant.now());
            return minutes + " phút";
        }
        if (seconds < 86400) {
            long hours = ChronoUnit.HOURS.between(instant, Instant.now());
            return hours + " giờ";
        }
        LocalDateTime localDateTime = instant.atZone(ZoneId.systemDefault()).toLocalDateTime();
        java.time.format.DateTimeFormatter formatter = java.time.format.DateTimeFormatter.ISO_DATE;

        return localDateTime.format(formatter);
    }
}
