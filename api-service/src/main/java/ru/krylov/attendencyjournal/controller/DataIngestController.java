package ru.krylov.attendencyjournal.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.krylov.attendencyjournal.dto.CheckinRequest;

@RestController
@RequestMapping("/api/data")
public class DataIngestController {

    private final KafkaTemplate<String, CheckinRequest> kafkaTemplate;
    private final String checkinsTopic;

    public DataIngestController(
            @Qualifier("checkinKafkaTemplate") KafkaTemplate<String, CheckinRequest> kafkaTemplate,
            @Value("${kafka.topic.checkins}") String checkinsTopic) {
        this.kafkaTemplate = kafkaTemplate;
        this.checkinsTopic = checkinsTopic;
    }

    @PostMapping("/batches")
    public ResponseEntity<Void> addBatch(@RequestBody CheckinRequest request) {
        kafkaTemplate.send(checkinsTopic, request);
        return ResponseEntity.accepted().build();
    }
}
