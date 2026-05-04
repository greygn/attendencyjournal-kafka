package ru.krylov.attendencyjournal.controller;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestClient;
import ru.krylov.attendencyjournal.dto.CheckinRequest;
import ru.krylov.attendencyjournal.support.DataServiceRestSupport;

@RestController
@RequestMapping("/checkins")
public class CheckinGatewayController {

    private final KafkaTemplate<String, CheckinRequest> kafkaTemplate;
    private final String checkinsTopic;
    private final RestClient dataServiceRestClient;

    public CheckinGatewayController(
            @Qualifier("checkinKafkaTemplate") KafkaTemplate<String, CheckinRequest> kafkaTemplate,
            @Value("${kafka.topic.checkins}") String checkinsTopic,
            RestClient dataServiceRestClient) {
        this.kafkaTemplate = kafkaTemplate;
        this.checkinsTopic = checkinsTopic;
        this.dataServiceRestClient = dataServiceRestClient;
    }

    @PostMapping
    public ResponseEntity<Void> markAttendance(@RequestBody CheckinRequest request) {
        kafkaTemplate.send(checkinsTopic, request);
        return ResponseEntity.accepted().build();
    }

    @GetMapping("/student/{id}/count")
    public ResponseEntity<String> studentAttendanceCount(@PathVariable Long id) {
        return DataServiceRestSupport.get(dataServiceRestClient, "/checkins/student/" + id + "/count");
    }
}
