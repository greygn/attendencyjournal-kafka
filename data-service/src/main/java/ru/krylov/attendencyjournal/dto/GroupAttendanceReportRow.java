package ru.krylov.attendencyjournal.dto;

public class GroupAttendanceReportRow {

    private String groupName;
    private long checkinCount;

    public GroupAttendanceReportRow() {
    }

    public GroupAttendanceReportRow(String groupName, long checkinCount) {
        this.groupName = groupName;
        this.checkinCount = checkinCount;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public long getCheckinCount() {
        return checkinCount;
    }

    public void setCheckinCount(long checkinCount) {
        this.checkinCount = checkinCount;
    }
}
