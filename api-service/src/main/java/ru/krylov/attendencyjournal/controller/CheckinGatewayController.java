package ru.krylov.attendencyjournal.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.krylov.attendencyjournal.client.DataServiceClient;
import ru.krylov.attendencyjournal.dto.CheckinRequest;
import ru.krylov.attendencyjournal.publisher.KafkaPublisher;

/* REST контроллер для управления присутствием студентов.
   Регистрирует присутствие (асинхронно) и получает статистику. */
@RestController
@RequestMapping("/checkins")
public class CheckinGatewayController {

    private final KafkaPublisher kafkaPublisher;
    private final DataServiceClient dataServiceClient;

    public CheckinGatewayController(
            KafkaPublisher kafkaPublisher,
            DataServiceClient dataServiceClient) {
        this.kafkaPublisher = kafkaPublisher;
        this.dataServiceClient = dataServiceClient;
    }

    /*
     * Регистрирует присутствие студента (асинхронно в Kafka).
     * Возвращает 202 Accepted.
     */
    @PostMapping
    public ResponseEntity<Void> markAttendance(@RequestBody CheckinRequest request) {
        kafkaPublisher.publishCheckin(request);
        return ResponseEntity.accepted().build();
    }

    /* Получает количество присутствий студента. */
    @GetMapping("/student/{id}/count")
    public ResponseEntity<String> studentAttendanceCount(@PathVariable Long id) {
        return dataServiceClient.getStudentAttendanceCount(id);
    }
}
