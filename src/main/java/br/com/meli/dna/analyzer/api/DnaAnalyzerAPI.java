package br.com.meli.dna.analyzer.api;

import br.com.meli.dna.analyzer.api.dto.DnaRequestDTO;
import br.com.meli.dna.analyzer.api.dto.DnaResponseDTO;
import br.com.meli.dna.analyzer.api.dto.DnaStatsDTO;
import br.com.meli.dna.analyzer.business.objects.DnaAnalysisResult;
import br.com.meli.dna.analyzer.facade.DnaAnalyzerFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static br.com.meli.dna.analyzer.business.objects.DnaAnalysisResult.Failure;
import static br.com.meli.dna.analyzer.business.objects.DnaAnalysisResult.Success;
import static br.com.meli.dna.analyzer.business.objects.DnaSpecies.SIMIAN;
import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.http.ResponseEntity.ok;
import static org.springframework.http.ResponseEntity.status;

@RestController
@RequestMapping("dna")
public class DnaAnalyzerAPI {
    @Autowired
    private DnaAnalyzerFacade dnaAnalyzerFacade;

    @PostMapping(value = "simian", consumes = APPLICATION_JSON_VALUE)
    public ResponseEntity analyzeSimian(@RequestBody DnaRequestDTO dna) {
        DnaAnalysisResult result = dnaAnalyzerFacade.analyze(dna.dna());

        if (result instanceof Success success) {
            return ok(new DnaResponseDTO(success.species() == SIMIAN));
        }

        return status(BAD_REQUEST).body((Failure) result);
    }

    @GetMapping(value = "stats", produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<DnaStatsDTO> getStats() {
        var stats = dnaAnalyzerFacade.getStats();

        return ok(new DnaStatsDTO(stats.getHumans(), stats.getSimians(), stats.getRatio()));
    }
}
