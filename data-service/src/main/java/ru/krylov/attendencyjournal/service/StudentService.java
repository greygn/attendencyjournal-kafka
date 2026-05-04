package ru.krylov.attendencyjournal.service;

import org.springframework.stereotype.Service;
import ru.krylov.attendencyjournal.entity.Student;
import ru.krylov.attendencyjournal.entity.StudyGroup;
import ru.krylov.attendencyjournal.repository.StudentRepository;
import ru.krylov.attendencyjournal.repository.StudyGroupRepository;

import java.util.List;

@Service
public class StudentService {

    private final StudentRepository studentRepository;
    private final StudyGroupRepository groupRepository;

    public StudentService(StudentRepository studentRepository,
                          StudyGroupRepository groupRepository) {
        this.studentRepository = studentRepository;
        this.groupRepository = groupRepository;
    }

    public Student create(String name, Long groupId) {

        StudyGroup group = groupRepository.findById(groupId)
                .orElseThrow(() -> new RuntimeException("Group not found"));

        Student student = new Student();
        student.setName(name);
        student.setGroup(group);

        return studentRepository.save(student);
    }

    public List<Student> getAll() {
        return studentRepository.findAll();
    }
}