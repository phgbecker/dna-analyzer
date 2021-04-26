package br.com.meli.dna.analyzer.facade;

import br.com.meli.dna.analyzer.business.DnaAnalyzerBusiness;
import br.com.meli.dna.analyzer.business.converter.DnaAnalyzerConverter;
import br.com.meli.dna.analyzer.business.objects.Dna;
import br.com.meli.dna.analyzer.business.objects.DnaAnalysisResult;
import br.com.meli.dna.analyzer.business.objects.DnaNitrogenBase;
import br.com.meli.dna.analyzer.business.sort.*;
import br.com.meli.dna.analyzer.service.DnaAnalyzerService;
import br.com.meli.dna.analyzer.view.DnaStatsView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static br.com.meli.dna.analyzer.business.objects.DnaAnalysisResult.Failure;
import static br.com.meli.dna.analyzer.business.objects.DnaAnalysisResult.Success;
import static br.com.meli.dna.analyzer.business.objects.DnaSpecies.HUMAN;
import static br.com.meli.dna.analyzer.business.objects.DnaSpecies.SIMIAN;
import static org.springframework.util.CollectionUtils.isEmpty;

@Service
public class DnaAnalyzerFacade {
    @Autowired
    private DnaAnalyzerService dnaAnalyzerService;

    private static final List<DnaSequenceSort> DNA_SEQUENCE_SORTERS = List.of(
            new DnaNaturalOrderSorter(),
            new DnaVerticalSequenceSort(),
            new DnaDiagonalForwardSequenceSort(),
            new DnaDiagonalBackwardsSequenceSort()
    );

    public DnaAnalysisResult analyze(List<String> dna) {
        DnaAnalysisResult result = analyze(new DnaAnalyzerConverter().fromStringToDna(dna));

        if (result instanceof Success success) {
            dnaAnalyzerService.save(dna, success.species());
        }

        return result;
    }

    private DnaAnalysisResult analyze(Dna dna) {
        try {
            var quadruples = 0;

            if (isEmpty(dna.nitrogenBases())) {
                return new Failure("The given DNA is empty");
            }

            for (DnaSequenceSort sorter : DNA_SEQUENCE_SORTERS) {
                Dna newSequence = sorter.sort(dna);

                for (DnaNitrogenBase[] nitrogenBases : newSequence.nitrogenBases()) {
                    quadruples = new DnaAnalyzerBusiness().findQuadruplesFrom(nitrogenBases) + quadruples;

                    if (quadruples >= 2) {
                        return new Success(SIMIAN);
                    }
                }
            }

            return new Success(HUMAN);
        } catch (Exception e) {
            return new Failure("Oops, the DNA analysis has failed. Details: " + e.getLocalizedMessage());
        }
    }

    public DnaStatsView getStats() {
        return dnaAnalyzerService.getStats();
    }
}
