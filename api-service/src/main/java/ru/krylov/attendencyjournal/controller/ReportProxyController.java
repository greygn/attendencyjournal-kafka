package ru.krylov.attendencyjournal.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.krylov.attendencyjournal.client.DataServiceClient;

/* REST контроллер для получения отчетов по посещаемости.
   Прокси для отчетов. */
@RestController
@RequestMapping("/api/reports")
public class ReportProxyController {

    private final DataServiceClient dataServiceClient;

    public ReportProxyController(DataServiceClient dataServiceClient) {
        this.dataServiceClient = dataServiceClient;
    }

    /* Отчет по посещаемости по группам. */
    @GetMapping("/attendance-by-group")
    public ResponseEntity<String> attendanceByGroup() {
        return dataServiceClient.getAttendanceByGroup();
    }

    /* Отчет по посещаемости по занятиям. */
    @GetMapping("/attendance-by-lesson")
    public ResponseEntity<String> attendanceByLesson() {
        return dataServiceClient.getAttendanceByLesson();
    }

    /* Сводный отчет по посещаемости. */
    @GetMapping("/summary")
    public ResponseEntity<String> summary() {
        return dataServiceClient.getAttendanceSummary();
    }
}
