package ru.krylov.attendencyjournal.service;

import org.springframework.stereotype.Service;
import ru.krylov.attendencyjournal.dto.CheckinResponse;
import ru.krylov.attendencyjournal.entity.Checkin;
import ru.krylov.attendencyjournal.repository.CheckinRepository;

import java.util.List;

@Service
public class SearchService {

    private final CheckinRepository checkinRepository;

    public SearchService(CheckinRepository checkinRepository) {
        this.checkinRepository = checkinRepository;
    }

    public List<CheckinResponse> searchCheckins(Long studentId, Long lessonId) {
        return checkinRepository.search(studentId, lessonId).stream()
                .map(this::toResponse)
                .toList();
    }

    private CheckinResponse toResponse(Checkin c) {
        CheckinResponse r = new CheckinResponse();
        r.setId(c.getId());
        r.setLessonId(c.getLesson().getId());
        r.setLessonName(c.getLesson().getName());
        r.setStudentId(c.getStudent().getId());
        r.setStudentName(c.getStudent().getName());
        return r;
    }
}
