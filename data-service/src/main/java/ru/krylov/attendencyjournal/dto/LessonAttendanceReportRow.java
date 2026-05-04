package ru.krylov.attendencyjournal.dto;

public class LessonAttendanceReportRow {

    private String lessonName;
    private long checkinCount;

    public LessonAttendanceReportRow() {
    }

    public LessonAttendanceReportRow(String lessonName, long checkinCount) {
        this.lessonName = lessonName;
        this.checkinCount = checkinCount;
    }

    public String getLessonName() {
        return lessonName;
    }

    public void setLessonName(String lessonName) {
        this.lessonName = lessonName;
    }

    public long getCheckinCount() {
        return checkinCount;
    }

    public void setCheckinCount(long checkinCount) {
        this.checkinCount = checkinCount;
    }
}
