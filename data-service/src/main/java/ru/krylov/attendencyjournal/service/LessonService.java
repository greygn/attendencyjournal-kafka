package ru.krylov.attendencyjournal.service;

import org.springframework.stereotype.Service;
import ru.krylov.attendencyjournal.entity.Lesson;
import ru.krylov.attendencyjournal.entity.StudyGroup;
import ru.krylov.attendencyjournal.repository.LessonRepository;
import ru.krylov.attendencyjournal.repository.StudyGroupRepository;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class LessonService {

    private final LessonRepository lessonRepository;
    private final StudyGroupRepository groupRepository;

    public LessonService(LessonRepository lessonRepository,
                         StudyGroupRepository groupRepository) {
        this.lessonRepository = lessonRepository;
        this.groupRepository = groupRepository;
    }

    public Lesson create(String name,
                         LocalDateTime datetime,
                         Set<Long> groupIds) {

        if (datetime.isBefore(LocalDateTime.now())) {
            throw new RuntimeException("Lesson date must be in future");
        }

        Set<StudyGroup> groups = new HashSet<>();

        for (Long id : groupIds) {
            StudyGroup group = groupRepository.findById(id)
                    .orElseThrow(() -> new RuntimeException("Group not found: " + id));
            groups.add(group);
        }

        Lesson lesson = new Lesson();
        lesson.setName(name);
        lesson.setLessonDatetime(datetime);
        lesson.setGroups(groups);

        return lessonRepository.save(lesson);
    }

    public List<Lesson> getAll() {
        return lessonRepository.findAll();
    }
}
