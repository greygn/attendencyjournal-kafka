package ru.krylov.attendencyjournal.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.krylov.attendencyjournal.dto.CreateGroupRequest;
import ru.krylov.attendencyjournal.entity.StudyGroup;
import ru.krylov.attendencyjournal.service.StudyGroupService;

import java.util.List;

@RestController
@RequestMapping("/groups")
public class StudyGroupController {

    private final StudyGroupService service;

    public StudyGroupController(StudyGroupService service) {
        this.service = service;
    }

    @PostMapping
    public StudyGroup create(@RequestBody CreateGroupRequest request) {
        return service.create(
                request.getName(),
                request.getCourse());
    }

    @GetMapping
    public List<StudyGroup> getAll() {
        return service.getAll();
    }
}