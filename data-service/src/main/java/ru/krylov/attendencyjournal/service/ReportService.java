package ru.krylov.attendencyjournal.service;

import org.springframework.stereotype.Service;
import ru.krylov.attendencyjournal.dto.AttendanceSummaryReport;
import ru.krylov.attendencyjournal.dto.GroupAttendanceReportRow;
import ru.krylov.attendencyjournal.dto.LessonAttendanceReportRow;
import ru.krylov.attendencyjournal.repository.CheckinRepository;

import java.util.List;

@Service
public class ReportService {

    private final CheckinRepository checkinRepository;

    public ReportService(CheckinRepository checkinRepository) {
        this.checkinRepository = checkinRepository;
    }

    public List<GroupAttendanceReportRow> attendanceByGroup() {
        return checkinRepository.countCheckinsByGroup().stream()
                .map(row -> new GroupAttendanceReportRow((String) row[0], (Long) row[1]))
                .toList();
    }

    public List<LessonAttendanceReportRow> attendanceByLesson() {
        return checkinRepository.countCheckinsByLesson().stream()
                .map(row -> new LessonAttendanceReportRow((String) row[0], (Long) row[1]))
                .toList();
    }

    public AttendanceSummaryReport summary() {
        long total = checkinRepository.count();
        long students = checkinRepository.countDistinctStudentsWithCheckins();
        long lessons = checkinRepository.countDistinctLessonsWithCheckins();
        return new AttendanceSummaryReport(total, students, lessons);
    }
}
