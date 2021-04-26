package br.com.meli.dna.analyzer.business.objects;

import br.com.meli.dna.analyzer.api.exception.DnaAnalyzerInvalidSequenceException;

import java.util.Arrays;

public enum DnaNitrogenBase {
    ADENINE("A"),
    CYTOSINE("C"),
    GUANINE("G"),
    THYMINE("T");

    private final String code;

    DnaNitrogenBase(String code) {
        this.code = code;
    }

    public static DnaNitrogenBase fromCode(String code) {
        return Arrays.stream(values())
                .filter(nitrogen -> nitrogen.code.equals(code.toUpperCase()))
                .findAny()
                .orElseThrow(DnaAnalyzerInvalidSequenceException::new);
    }

    @Override
    public String toString() {
        return code;
    }
}
