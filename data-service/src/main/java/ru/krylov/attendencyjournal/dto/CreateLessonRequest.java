package ru.krylov.attendencyjournal.dto;

import java.time.LocalDateTime;
import java.util.List;

public class CreateLessonRequest {
    private String name;
    private LocalDateTime datetime;
    private List<Long> groupIds;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDateTime getDatetime() {
        return datetime;
    }

    public void setDatetime(LocalDateTime datetime) {
        this.datetime = datetime;
    }

    public List<Long> getGroupIds() {
        return groupIds;
    }

    public void setGroupIds(List<Long> groupIds) {
        this.groupIds = groupIds;
    }
}
