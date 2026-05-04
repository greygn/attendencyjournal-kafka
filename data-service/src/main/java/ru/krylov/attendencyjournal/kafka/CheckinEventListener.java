package ru.krylov.attendencyjournal.kafka;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;
import ru.krylov.attendencyjournal.dto.CheckinRequest;
import ru.krylov.attendencyjournal.service.CheckinService;

@Component
public class CheckinEventListener {

    private static final Logger log = LoggerFactory.getLogger(CheckinEventListener.class);

    private final CheckinService checkinService;

    public CheckinEventListener(CheckinService checkinService) {
        this.checkinService = checkinService;
    }

    @KafkaListener(topics = "${kafka.topic.checkins}", groupId = "${spring.kafka.consumer.group-id}")
    public void onCheckin(CheckinRequest request) {
        try {
            checkinService.markAttendance(request.getLessonId(), request.getStudentId());
        } catch (RuntimeException ex) {
            log.warn("Check-in event skipped: {}", ex.getMessage());
        }
    }
}
