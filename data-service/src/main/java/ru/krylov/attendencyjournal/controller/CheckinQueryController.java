package ru.krylov.attendencyjournal.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.krylov.attendencyjournal.service.CheckinService;

@RestController
@RequestMapping("/checkins")
public class CheckinQueryController {

    private final CheckinService checkinService;

    public CheckinQueryController(CheckinService checkinService) {
        this.checkinService = checkinService;
    }

    @GetMapping("/student/{id}/count")
    public long getStudentAttendanceCount(@PathVariable Long id) {
        return checkinService.getStudentAttendanceCount(id);
    }
}
