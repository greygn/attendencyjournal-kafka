package ru.krylov.attendencyjournal.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.krylov.attendencyjournal.entity.StudyGroup;

public interface StudyGroupRepository extends JpaRepository<StudyGroup, Long> {}