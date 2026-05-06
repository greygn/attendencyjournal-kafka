package ru.krylov.attendencyjournal.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.krylov.attendencyjournal.dto.CheckinRequest;
import ru.krylov.attendencyjournal.publisher.KafkaPublisher;

/* REST контроллер для пакетной загрузки данных о присутствии.
   Отправляет в Kafka асинхронно. */
@RestController
@RequestMapping("/api/data")
public class DataIngestController {

    private final KafkaPublisher kafkaPublisher;

    public DataIngestController(KafkaPublisher kafkaPublisher) {
        this.kafkaPublisher = kafkaPublisher;
    }

    /*
     * Добавляет пакет отметок в Kafka (асинхронно).
     * Возвращает 202 Accepted.
     */
    @PostMapping("/batches")
    public ResponseEntity<Void> addBatch(@RequestBody CheckinRequest request) {
        kafkaPublisher.publishCheckin(request);
        return ResponseEntity.accepted().build();
    }
}
