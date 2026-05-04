package ru.krylov.attendencyjournal.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestClient;
import ru.krylov.attendencyjournal.support.DataServiceRestSupport;

@RestController
public class ReferenceDataProxyController {

    private final RestClient dataServiceRestClient;

    public ReferenceDataProxyController(RestClient dataServiceRestClient) {
        this.dataServiceRestClient = dataServiceRestClient;
    }

    @GetMapping("/groups")
    public ResponseEntity<String> getGroups() {
        return DataServiceRestSupport.get(dataServiceRestClient, "/groups");
    }

    @PostMapping("/groups")
    public ResponseEntity<String> postGroups(@RequestBody String body) {
        return DataServiceRestSupport.postJson(dataServiceRestClient, "/groups", body);
    }

    @GetMapping("/students")
    public ResponseEntity<String> getStudents() {
        return DataServiceRestSupport.get(dataServiceRestClient, "/students");
    }

    @PostMapping("/students")
    public ResponseEntity<String> postStudents(@RequestBody String body) {
        return DataServiceRestSupport.postJson(dataServiceRestClient, "/students", body);
    }

    @GetMapping("/lessons")
    public ResponseEntity<String> getLessons() {
        return DataServiceRestSupport.get(dataServiceRestClient, "/lessons");
    }

    @PostMapping("/lessons")
    public ResponseEntity<String> postLessons(@RequestBody String body) {
        return DataServiceRestSupport.postJson(dataServiceRestClient, "/lessons", body);
    }
}
