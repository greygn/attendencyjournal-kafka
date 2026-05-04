package ru.krylov.attendencyjournal.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "lessons")
public class Lesson {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private LocalDateTime lessonDatetime;

    @ManyToMany
    @JoinTable(name = "lesson_groups", joinColumns = @JoinColumn(name = "lesson_id"), inverseJoinColumns = @JoinColumn(name = "group_id"))
    private Set<StudyGroup> groups = new HashSet<>();

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getLessonDatetime() {
        return lessonDatetime;
    }

    public void setLessonDatetime(LocalDateTime lessonDatetime) {
        this.lessonDatetime = lessonDatetime;
    }

    public Set<StudyGroup> getGroups() {
        return groups;
    }

    public void setGroups(Set<StudyGroup> groups) {
        this.groups = groups;
    }
}