package ru.krylov.attendencyjournal.dto;

public class AttendanceSummaryReport {

    private long totalCheckins;
    private long distinctStudentsWithCheckins;
    private long distinctLessonsWithCheckins;

    public AttendanceSummaryReport() {
    }

    public AttendanceSummaryReport(long totalCheckins, long distinctStudentsWithCheckins, long distinctLessonsWithCheckins) {
        this.totalCheckins = totalCheckins;
        this.distinctStudentsWithCheckins = distinctStudentsWithCheckins;
        this.distinctLessonsWithCheckins = distinctLessonsWithCheckins;
    }

    public long getTotalCheckins() {
        return totalCheckins;
    }

    public void setTotalCheckins(long totalCheckins) {
        this.totalCheckins = totalCheckins;
    }

    public long getDistinctStudentsWithCheckins() {
        return distinctStudentsWithCheckins;
    }

    public void setDistinctStudentsWithCheckins(long distinctStudentsWithCheckins) {
        this.distinctStudentsWithCheckins = distinctStudentsWithCheckins;
    }

    public long getDistinctLessonsWithCheckins() {
        return distinctLessonsWithCheckins;
    }

    public void setDistinctLessonsWithCheckins(long distinctLessonsWithCheckins) {
        this.distinctLessonsWithCheckins = distinctLessonsWithCheckins;
    }
}
