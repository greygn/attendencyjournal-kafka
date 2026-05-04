package ru.krylov.attendencyjournal.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ru.krylov.attendencyjournal.entity.Checkin;

import java.util.List;

public interface CheckinRepository extends JpaRepository<Checkin, Long> {

    boolean existsByLessonIdAndStudentId(Long lessonId, Long studentId);

    long countByStudentId(Long studentId);

    @Query("SELECT c FROM Checkin c WHERE (:studentId IS NULL OR c.student.id = :studentId) "
            + "AND (:lessonId IS NULL OR c.lesson.id = :lessonId)")
    List<Checkin> search(@Param("studentId") Long studentId, @Param("lessonId") Long lessonId);

    @Query("SELECT g.name, COUNT(c.id) FROM Checkin c JOIN c.student s JOIN s.group g GROUP BY g.id, g.name ORDER BY g.name")
    List<Object[]> countCheckinsByGroup();

    @Query("SELECT l.name, COUNT(c.id) FROM Checkin c JOIN c.lesson l GROUP BY l.id, l.name ORDER BY l.name")
    List<Object[]> countCheckinsByLesson();

    @Query("SELECT COUNT(DISTINCT c.student.id) FROM Checkin c")
    long countDistinctStudentsWithCheckins();

    @Query("SELECT COUNT(DISTINCT c.lesson.id) FROM Checkin c")
    long countDistinctLessonsWithCheckins();
}
