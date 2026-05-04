package ru.krylov.attendencyjournal.dto;

import com.fasterxml.jackson.annotation.JsonAlias;

public class CreateGroupRequest {
    private String name;
    @JsonAlias("courseNumber")
    private Integer course;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getCourse() {
        return course;
    }

    public void setCourse(Integer course) {
        this.course = course;
    }
}
