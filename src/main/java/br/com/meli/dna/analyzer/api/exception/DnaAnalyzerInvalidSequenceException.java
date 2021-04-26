package br.com.meli.dna.analyzer.api.exception;

import br.com.meli.dna.analyzer.business.objects.DnaNitrogenBase;

import java.util.Arrays;

public class DnaAnalyzerInvalidSequenceException extends RuntimeException {

    public DnaAnalyzerInvalidSequenceException() {
        super("The given sequence is invalid. Possible values are: " + Arrays.toString(DnaNitrogenBase.values()));
    }
}
