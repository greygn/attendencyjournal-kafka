package ru.krylov.attendencyjournal.service;

import org.springframework.stereotype.Service;
import ru.krylov.attendencyjournal.entity.StudyGroup;
import ru.krylov.attendencyjournal.repository.StudyGroupRepository;

import java.util.List;

@Service
public class StudyGroupService {

    private final StudyGroupRepository repository;

    public StudyGroupService(StudyGroupRepository repository) {
        this.repository = repository;
    }

    public StudyGroup create(String name, Integer course) {
        StudyGroup group = new StudyGroup();
        group.setName(name);
        group.setCourse(course != null ? course : 1);
        return repository.save(group);
    }

    public List<StudyGroup> getAll() {
        return repository.findAll();
    }
}