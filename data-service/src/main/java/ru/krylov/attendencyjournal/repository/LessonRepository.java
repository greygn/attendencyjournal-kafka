package ru.krylov.attendencyjournal.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.krylov.attendencyjournal.entity.Lesson;

public interface LessonRepository extends JpaRepository<Lesson, Long> {}