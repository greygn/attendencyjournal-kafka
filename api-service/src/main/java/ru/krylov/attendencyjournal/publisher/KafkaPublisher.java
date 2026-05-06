package ru.krylov.attendencyjournal.publisher;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import ru.krylov.attendencyjournal.dto.CheckinRequest;

/* Сервис для публикации сообщений в Kafka.
   Инкапсулирует логику отправки в очередь с partition key.
   Partition key = studentId гарантирует порядок обработки. */
@Service
public class KafkaPublisher {

    private static final Logger log = LoggerFactory.getLogger(KafkaPublisher.class);

    private final KafkaTemplate<String, CheckinRequest> kafkaTemplate;
    private final String checkinsTopic;

    public KafkaPublisher(
            KafkaTemplate<String, CheckinRequest> kafkaTemplate,
            @Value("${kafka.topic.checkins}") String checkinsTopic) {
        this.kafkaTemplate = kafkaTemplate;
        this.checkinsTopic = checkinsTopic;
    }

    /*
     * Публикует событие присутствия студента в Kafka с partition key = studentId.
     * Гарантирует FIFO обработку для каждого студента.
     */
    public void publishCheckin(CheckinRequest request) {
        String partitionKey = String.valueOf(request.getStudentId());

        kafkaTemplate.send(checkinsTopic, partitionKey, request);

        log.info("Published checkin for student={} lesson={} to topic={}",
                request.getStudentId(), request.getLessonId(), checkinsTopic);
    }

    /* Пакетная отправка нескольких событий в Kafka. */
    public void publishCheckinsBatch(java.util.List<CheckinRequest> requests) {
        requests.forEach(this::publishCheckin);
        log.info("Published batch of {} checkins to topic={}", requests.size(), checkinsTopic);
    }
}
