package br.com.meli.dna.analyzer.business;

import br.com.meli.dna.analyzer.business.objects.DnaNitrogenBase;
import org.junit.jupiter.api.Test;

import static br.com.meli.dna.analyzer.business.objects.DnaNitrogenBase.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

class DnaAnalyzerBusinessTest {

    @Test
    void givenSequenceWhenFindQuadruplesShouldReturnAsExpected() {
        var expected = 2;
        var actual = new DnaAnalyzerBusiness().findQuadruplesFrom(
                new DnaNitrogenBase[]{GUANINE, CYTOSINE, ADENINE, ADENINE, ADENINE, ADENINE, THYMINE, THYMINE, THYMINE, THYMINE, CYTOSINE, GUANINE}
        );

        assertEquals(expected, actual);
    }

    @Test
    void givenEmptySequenceWhenFindQuadruplesShouldReturnAsExpected() {
        var expected = 0;
        var actual = new DnaAnalyzerBusiness().findQuadruplesFrom(
                new DnaNitrogenBase[]{GUANINE, CYTOSINE, ADENINE, THYMINE}
        );

        assertEquals(expected, actual);
    }
}