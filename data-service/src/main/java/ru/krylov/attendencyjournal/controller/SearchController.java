package ru.krylov.attendencyjournal.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.krylov.attendencyjournal.dto.CheckinResponse;
import ru.krylov.attendencyjournal.service.SearchService;

import java.util.List;

@RestController
@RequestMapping("/search")
public class SearchController {

    private final SearchService searchService;

    public SearchController(SearchService searchService) {
        this.searchService = searchService;
    }

    @GetMapping("/checkins")
    public List<CheckinResponse> searchCheckins(
            @RequestParam(required = false) Long studentId,
            @RequestParam(required = false) Long lessonId) {
        return searchService.searchCheckins(studentId, lessonId);
    }
}
