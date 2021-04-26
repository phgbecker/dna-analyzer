package br.com.meli.dna.analyzer.business.converter;

import br.com.meli.dna.analyzer.api.exception.DnaAnalyzerInvalidSequenceException;
import br.com.meli.dna.analyzer.business.objects.Dna;
import br.com.meli.dna.analyzer.business.objects.DnaNitrogenBase;

import java.util.ArrayList;
import java.util.List;

import static java.util.Objects.isNull;

public class DnaAnalyzerConverter {

    public Dna fromStringToDna(List<String> dnaString) {
        var sequences = new ArrayList<DnaNitrogenBase[]>();

        if (isNull(dnaString) || dnaString.isEmpty()) {
            throw new DnaAnalyzerInvalidSequenceException();
        }

        dnaString.stream()
                .peek(sequence -> {
                    if (sequence.isEmpty()) {
                        throw new DnaAnalyzerInvalidSequenceException();
                    }

                }).forEach(sequence -> {
                    var nitrogenBases = new DnaNitrogenBase[sequence.length()];
                    for (var i = 0; i < sequence.length(); i++) {
                        nitrogenBases[i] = DnaNitrogenBase.fromCode(String.valueOf(sequence.charAt(i)));
                    }

                    sequences.add(nitrogenBases);
                }
        );

        return new Dna(sequences);
    }
}
