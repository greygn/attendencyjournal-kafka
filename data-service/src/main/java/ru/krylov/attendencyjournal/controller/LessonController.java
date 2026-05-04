package ru.krylov.attendencyjournal.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.krylov.attendencyjournal.dto.CreateLessonRequest;
import ru.krylov.attendencyjournal.entity.Lesson;
import ru.krylov.attendencyjournal.service.LessonService;

import java.util.HashSet;
import java.util.List;

@RestController
@RequestMapping("/lessons")
public class LessonController {

    private final LessonService service;

    public LessonController(LessonService service) {
        this.service = service;
    }

    @PostMapping
    public Lesson create(@RequestBody CreateLessonRequest request) {
        return service.create(
                request.getName(),
                request.getDatetime(),
                new HashSet<>(request.getGroupIds()));
    }

    @GetMapping
    public List<Lesson> getAll() {
        return service.getAll();
    }
}