package br.com.meli.dna.analyzer.facade;

import br.com.meli.dna.analyzer.api.exception.DnaAnalyzerInvalidSequenceException;
import br.com.meli.dna.analyzer.service.DnaAnalyzerService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Arrays;

import static br.com.meli.dna.analyzer.business.objects.DnaAnalysisResult.Failure;
import static br.com.meli.dna.analyzer.business.objects.DnaAnalysisResult.Success;
import static br.com.meli.dna.analyzer.business.objects.DnaSpecies.HUMAN;
import static br.com.meli.dna.analyzer.business.objects.DnaSpecies.SIMIAN;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(SpringExtension.class)
class DnaAnalyzerFacadeTest {
    @Mock
    private DnaAnalyzerService dnaAnalyzerService;

    @InjectMocks
    private DnaAnalyzerFacade dnaAnalyzerFacade;

    @Test
    void givenDnaWhenAnalyzeShouldReturnSimian() {
        var expected = new Success(SIMIAN);

        assertEquals(expected, dnaAnalyzerFacade.analyze(Arrays.asList("CTGAGA", "CTATGC", "TATTGT", "AGGGGG", "CCCCTA", "TCACTG")));
        assertEquals(expected, dnaAnalyzerFacade.analyze(Arrays.asList("CTGAGA", "CTATGC", "TATTGT", "AGAGGG", "CCCCTA", "TCACTG")));
        assertEquals(expected, dnaAnalyzerFacade.analyze(Arrays.asList("CTGAGA", "CCATGC", "TACTGT", "AGACAG", "CCCCTA", "TCACTG")));
        assertEquals(expected, dnaAnalyzerFacade.analyze(Arrays.asList("CTGAGA", "CCATGC", "TACTGT", "AGACGG", "TGCTTA", "TCACTG")));
        assertEquals(expected, dnaAnalyzerFacade.analyze(Arrays.asList("CTGATA", "TGACAC", "GAGTGA", "AGCGGT", "GAATGA", "ATGACC")));
    }

    @Test
    void givenDnaWhenAnalyzeShouldReturnHuman() {
        var expected = new Success(HUMAN);

        assertEquals(expected, dnaAnalyzerFacade.analyze(Arrays.asList("CTGAGA", "CTATGC", "TATTAT", "AGAGGG", "AGCCTA", "TCACTG")));
        assertEquals(expected, dnaAnalyzerFacade.analyze(Arrays.asList("ACTG", "AAGC", "ATGT")));
        assertEquals(expected, dnaAnalyzerFacade.analyze(Arrays.asList("AC", "GC")));
    }

    @Test
    void givenInvalidDnaWhenAnalyzeShouldReturnFailure() {
        var expected = new Failure("Oops, the DNA analysis has failed. Details: Index 1 out of bounds for length 1");
        var actual = dnaAnalyzerFacade.analyze(Arrays.asList("C", "CT", "T"));

        assertEquals(expected, actual);
    }

    @Test
    void givenInvalidDnaWhenAnalyzeShouldReturnAsExpected() {
        assertThrows(DnaAnalyzerInvalidSequenceException.class, () ->
                dnaAnalyzerFacade.analyze(Arrays.asList())
        );
    }
}