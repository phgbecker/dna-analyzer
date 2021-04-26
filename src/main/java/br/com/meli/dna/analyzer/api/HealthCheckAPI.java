package br.com.meli.dna.analyzer.api;

import br.com.meli.dna.analyzer.api.dto.HealthCheckDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.http.ResponseEntity.ok;

@RestController
@RequestMapping("health-check")
public class HealthCheckAPI {

    @GetMapping(produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<HealthCheckDTO> status() {
        return ok(new HealthCheckDTO("I'm healthy :)"));
    }
}
