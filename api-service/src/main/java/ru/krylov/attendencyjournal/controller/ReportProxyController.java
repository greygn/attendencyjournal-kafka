package ru.krylov.attendencyjournal.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestClient;
import ru.krylov.attendencyjournal.support.DataServiceRestSupport;

@RestController
@RequestMapping("/api/reports")
public class ReportProxyController {

    private final RestClient dataServiceRestClient;

    public ReportProxyController(RestClient dataServiceRestClient) {
        this.dataServiceRestClient = dataServiceRestClient;
    }

    @GetMapping("/attendance-by-group")
    public ResponseEntity<String> attendanceByGroup() {
        return DataServiceRestSupport.get(dataServiceRestClient, "/reports/attendance-by-group");
    }

    @GetMapping("/attendance-by-lesson")
    public ResponseEntity<String> attendanceByLesson() {
        return DataServiceRestSupport.get(dataServiceRestClient, "/reports/attendance-by-lesson");
    }

    @GetMapping("/summary")
    public ResponseEntity<String> summary() {
        return DataServiceRestSupport.get(dataServiceRestClient, "/reports/summary");
    }
}
