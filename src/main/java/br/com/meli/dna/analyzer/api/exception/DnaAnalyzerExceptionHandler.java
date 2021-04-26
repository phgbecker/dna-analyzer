package br.com.meli.dna.analyzer.api.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import java.util.Map;

import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.ResponseEntity.status;

@RestControllerAdvice
public class DnaAnalyzerExceptionHandler {

    @ExceptionHandler(RuntimeException.class)
    protected ResponseEntity<Map<String, String>> handle(RuntimeException e, WebRequest request) {
        return status(BAD_REQUEST).body(Map.of("error", "Oops, the DNA analyses has failed. Details: " + e.getLocalizedMessage()));
    }
}
