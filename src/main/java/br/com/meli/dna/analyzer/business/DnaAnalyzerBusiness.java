package br.com.meli.dna.analyzer.business;

import br.com.meli.dna.analyzer.business.objects.DnaNitrogenBase;

import static java.util.Objects.nonNull;

public class DnaAnalyzerBusiness {

    public Integer findQuadruplesFrom(DnaNitrogenBase[] nitrogenBases) {
        var quadruples = 0;
        var nitrogenBaseOccurrence = 0;

        for (var sequence = 0; sequence < nitrogenBases.length; sequence++) {

            if (sequence + 1 < nitrogenBases.length &&
                    nonNull(nitrogenBases[sequence]) &&
                    nitrogenBases[sequence] == nitrogenBases[sequence + 1]
            ) {
                nitrogenBaseOccurrence = nitrogenBaseOccurrence == 0 ? 2 : nitrogenBaseOccurrence + 1;

                if (nitrogenBaseOccurrence % 4 == 0) {
                    nitrogenBaseOccurrence = 0;
                    quadruples++;
                }
            } else {
                nitrogenBaseOccurrence = 0;
            }

        }

        return quadruples;
    }
}
