package br.com.meli.dna.analyzer.business.converter;

import br.com.meli.dna.analyzer.api.exception.DnaAnalyzerInvalidSequenceException;
import br.com.meli.dna.analyzer.business.objects.Dna;
import br.com.meli.dna.analyzer.business.objects.DnaNitrogenBase;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static br.com.meli.dna.analyzer.business.objects.DnaNitrogenBase.*;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class DnaAnalyzerConverterTest {

    @Test
    void givenArrayWhenConvertToDnaShouldReturnAsExpected() {
        var expected = new Dna(
                Arrays.asList(
                        new DnaNitrogenBase[]{ADENINE, CYTOSINE, GUANINE, THYMINE, GUANINE, ADENINE},
                        new DnaNitrogenBase[]{ADENINE, ADENINE, CYTOSINE, THYMINE, GUANINE, ADENINE}
                )
        );

        var actual = new DnaAnalyzerConverter().fromStringToDna(Arrays.asList("ACGTGA", "AACTGA"));

        assertArrayEquals(expected.nitrogenBases().get(0), actual.nitrogenBases().get(0));
        assertArrayEquals(expected.nitrogenBases().get(1), actual.nitrogenBases().get(1));
    }

    @Test
    void givenNullArrayWhenConvertToDnaShouldReturnException() {
        assertThrows(DnaAnalyzerInvalidSequenceException.class, () ->
                new DnaAnalyzerConverter().fromStringToDna(null)
        );
    }

    @Test
    void givenEmptyArrayWhenConvertToDnaShouldReturnException() {
        assertThrows(DnaAnalyzerInvalidSequenceException.class, () ->
                new DnaAnalyzerConverter().fromStringToDna(Arrays.asList(""))
        );
    }

    @Test
    void givenInvalidArrayWhenConvertToDnaShouldReturnException() {
        assertThrows(DnaAnalyzerInvalidSequenceException.class, () ->
                new DnaAnalyzerConverter().fromStringToDna(Arrays.asList("-+/0z"))
        );
    }
}