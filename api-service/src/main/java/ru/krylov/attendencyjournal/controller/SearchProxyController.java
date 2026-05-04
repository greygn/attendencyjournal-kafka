package ru.krylov.attendencyjournal.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestClient;
import org.springframework.web.util.UriComponentsBuilder;
import ru.krylov.attendencyjournal.support.DataServiceRestSupport;

@RestController
@RequestMapping("/api/search")
public class SearchProxyController {

    private final RestClient dataServiceRestClient;

    public SearchProxyController(RestClient dataServiceRestClient) {
        this.dataServiceRestClient = dataServiceRestClient;
    }

    @GetMapping("/checkins")
    public ResponseEntity<String> searchCheckins(
            @RequestParam(required = false) Long studentId,
            @RequestParam(required = false) Long lessonId) {
        UriComponentsBuilder builder = UriComponentsBuilder.fromPath("/search/checkins");
        if (studentId != null) {
            builder.queryParam("studentId", studentId);
        }
        if (lessonId != null) {
            builder.queryParam("lessonId", lessonId);
        }
        return DataServiceRestSupport.get(dataServiceRestClient, builder.build().toUriString());
    }
}
