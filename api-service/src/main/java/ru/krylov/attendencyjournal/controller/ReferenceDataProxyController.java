package ru.krylov.attendencyjournal.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ru.krylov.attendencyjournal.client.DataServiceClient;

/* REST контроллер для доступа к справочным данным.
   Прокси для группы, студенты, занятия. */
@RestController
public class ReferenceDataProxyController {

    private final DataServiceClient dataServiceClient;

    public ReferenceDataProxyController(DataServiceClient dataServiceClient) {
        this.dataServiceClient = dataServiceClient;
    }

    /* Получает список всех групп. */
    @GetMapping("/groups")
    public ResponseEntity<String> getGroups() {
        return dataServiceClient.getGroups();
    }

    /* Создает новую группу. */
    @PostMapping("/groups")
    public ResponseEntity<String> postGroups(@RequestBody String body) {
        return dataServiceClient.createGroup(body);
    }

    /* Получает список всех студентов. */
    @GetMapping("/students")
    public ResponseEntity<String> getStudents() {
        return dataServiceClient.getStudents();
    }

    /* Создает нового студента. */
    @PostMapping("/students")
    public ResponseEntity<String> postStudents(@RequestBody String body) {
        return dataServiceClient.createStudent(body);
    }

    /* Получает список всех занятий. */
    @GetMapping("/lessons")
    public ResponseEntity<String> getLessons() {
        return dataServiceClient.getLessons();
    }

    /* Создает новое занятие. */
    @PostMapping("/lessons")
    public ResponseEntity<String> postLessons(@RequestBody String body) {
        return dataServiceClient.createLesson(body);
    }
}
