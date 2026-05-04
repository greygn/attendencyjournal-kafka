package ru.krylov.attendencyjournal.support;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.RestClientResponseException;

import java.nio.charset.StandardCharsets;

public final class DataServiceRestSupport {

    private DataServiceRestSupport() {
    }

    public static ResponseEntity<String> postJson(RestClient client, String path, String body) {
        try {
            return client.post()
                    .uri(path)
                    .contentType(MediaType.APPLICATION_JSON)
                    .body(body)
                    .retrieve()
                    .toEntity(String.class);
        } catch (RestClientResponseException ex) {
            return ResponseEntity.status(ex.getStatusCode())
                    .body(safeBody(ex));
        }
    }

    public static ResponseEntity<String> get(RestClient client, String pathOrUri) {
        try {
            return client.get()
                    .uri(pathOrUri)
                    .retrieve()
                    .toEntity(String.class);
        } catch (RestClientResponseException ex) {
            return ResponseEntity.status(ex.getStatusCode())
                    .body(safeBody(ex));
        }
    }

    private static String safeBody(RestClientResponseException ex) {
        String s = ex.getResponseBodyAsString(StandardCharsets.UTF_8);
        return s != null ? s : "";
    }
}
