package ru.krylov.attendencyjournal.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.krylov.attendencyjournal.dto.AttendanceSummaryReport;
import ru.krylov.attendencyjournal.dto.GroupAttendanceReportRow;
import ru.krylov.attendencyjournal.dto.LessonAttendanceReportRow;
import ru.krylov.attendencyjournal.service.ReportService;

import java.util.List;

@RestController
@RequestMapping("/reports")
public class ReportController {

    private final ReportService reportService;

    public ReportController(ReportService reportService) {
        this.reportService = reportService;
    }

    @GetMapping("/attendance-by-group")
    public List<GroupAttendanceReportRow> attendanceByGroup() {
        return reportService.attendanceByGroup();
    }

    @GetMapping("/attendance-by-lesson")
    public List<LessonAttendanceReportRow> attendanceByLesson() {
        return reportService.attendanceByLesson();
    }

    @GetMapping("/summary")
    public AttendanceSummaryReport summary() {
        return reportService.summary();
    }
}
