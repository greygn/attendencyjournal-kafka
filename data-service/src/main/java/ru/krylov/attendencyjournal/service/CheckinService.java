package ru.krylov.attendencyjournal.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.krylov.attendencyjournal.entity.Checkin;
import ru.krylov.attendencyjournal.entity.Lesson;
import ru.krylov.attendencyjournal.entity.Student;
import ru.krylov.attendencyjournal.repository.CheckinRepository;
import ru.krylov.attendencyjournal.repository.LessonRepository;
import ru.krylov.attendencyjournal.repository.StudentRepository;

@Service
public class CheckinService {

    private final CheckinRepository checkinRepository;
    private final LessonRepository lessonRepository;
    private final StudentRepository studentRepository;

    public CheckinService(CheckinRepository checkinRepository,
                          LessonRepository lessonRepository,
                          StudentRepository studentRepository) {
        this.checkinRepository = checkinRepository;
        this.lessonRepository = lessonRepository;
        this.studentRepository = studentRepository;
    }

    @Transactional
    public Checkin markAttendance(Long lessonId, Long studentId) {

        if (checkinRepository.existsByLessonIdAndStudentId(lessonId, studentId)) {
            throw new RuntimeException("Student already checked in");
        }

        Lesson lesson = lessonRepository.findById(lessonId)
                .orElseThrow(() -> new RuntimeException("Lesson not found"));

        Student student = studentRepository.findById(studentId)
                .orElseThrow(() -> new RuntimeException("Student not found"));

        if (!lesson.getGroups().contains(student.getGroup())) {
            throw new RuntimeException("Student not in lesson group");
        }

        Checkin checkin = new Checkin();
        checkin.setLesson(lesson);
        checkin.setStudent(student);

        return checkinRepository.save(checkin);
    }

    public long getStudentAttendanceCount(Long studentId) {
        return checkinRepository.countByStudentId(studentId);
    }
}