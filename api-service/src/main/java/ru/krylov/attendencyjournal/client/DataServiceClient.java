package ru.krylov.attendencyjournal.client;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.RestClientResponseException;

import java.nio.charset.StandardCharsets;

/* Клиент для взаимодействия с data-service.
   Инкапсулирует HTTP-запросы, обработку ошибок и логирование. */
@Service
public class DataServiceClient {

    private static final Logger log = LoggerFactory.getLogger(DataServiceClient.class);

    private final RestClient restClient;

    public DataServiceClient(RestClient dataServiceRestClient) {
        this.restClient = dataServiceRestClient;
    }

    /* Получает список всех групп. */
    public ResponseEntity<String> getGroups() {
        log.debug("Fetching groups from data-service");
        return get("/groups");
    }

    /* Создает новую группу. */
    public ResponseEntity<String> createGroup(String groupJson) {
        log.debug("Creating group in data-service");
        return postJson("/groups", groupJson);
    }

    /* Получает список всех студентов. */
    public ResponseEntity<String> getStudents() {
        log.debug("Fetching students from data-service");
        return get("/students");
    }

    /* Создает нового студента. */
    public ResponseEntity<String> createStudent(String studentJson) {
        log.debug("Creating student in data-service");
        return postJson("/students", studentJson);
    }

    /* Получает список всех занятий. */
    public ResponseEntity<String> getLessons() {
        log.debug("Fetching lessons from data-service");
        return get("/lessons");
    }

    /* Создает новое занятие. */
    public ResponseEntity<String> createLesson(String lessonJson) {
        log.debug("Creating lesson in data-service");
        return postJson("/lessons", lessonJson);
    }

    /* Получает количество присутствий студента. */
    public ResponseEntity<String> getStudentAttendanceCount(Long studentId) {
        log.debug("Fetching attendance count for student={}", studentId);
        return get("/checkins/student/" + studentId + "/count");
    }

    /* Получает отчет по посещаемости по группам. */
    public ResponseEntity<String> getAttendanceByGroup() {
        log.debug("Fetching attendance report by group");
        return get("/reports/attendance-by-group");
    }

    /* Получает отчет по посещаемости по занятиям. */
    public ResponseEntity<String> getAttendanceByLesson() {
        log.debug("Fetching attendance report by lesson");
        return get("/reports/attendance-by-lesson");
    }

    /* Получает сводный отчет по посещаемости. */
    public ResponseEntity<String> getAttendanceSummary() {
        log.debug("Fetching attendance summary report");
        return get("/reports/summary");
    }

    /* Выполняет поиск отметок о присутствии по фильтрам. */
    public ResponseEntity<String> searchCheckins(String path) {
        log.debug("Searching checkins with path={}", path);
        return get(path);
    }

    /* Выполняет GET запрос к data-service. */
    private ResponseEntity<String> get(String path) {
        try {
            return restClient.get()
                    .uri(path)
                    .retrieve()
                    .toEntity(String.class);
        } catch (RestClientResponseException ex) {
            log.error("Error in GET request to {}: status={}, message={}",
                    path, ex.getStatusCode(), ex.getMessage());
            return ResponseEntity.status(ex.getStatusCode())
                    .body(safeBody(ex));
        }
    }

    /* Выполняет POST запрос с JSON телом к data-service. */
    private ResponseEntity<String> postJson(String path, String body) {
        try {
            return restClient.post()
                    .uri(path)
                    .contentType(MediaType.APPLICATION_JSON)
                    .body(body)
                    .retrieve()
                    .toEntity(String.class);
        } catch (RestClientResponseException ex) {
            log.error("Error in POST request to {}: status={}, message={}",
                    path, ex.getStatusCode(), ex.getMessage());
            return ResponseEntity.status(ex.getStatusCode())
                    .body(safeBody(ex));
        }
    }

    /* Безопасно извлекает тело ошибки из исключения. */
    private String safeBody(RestClientResponseException ex) {
        String body = ex.getResponseBodyAsString(StandardCharsets.UTF_8);
        return body != null ? body : "";
    }
}
