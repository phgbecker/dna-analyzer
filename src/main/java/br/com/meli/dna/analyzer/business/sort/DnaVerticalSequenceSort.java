package br.com.meli.dna.analyzer.business.sort;

import br.com.meli.dna.analyzer.business.objects.Dna;
import br.com.meli.dna.analyzer.business.objects.DnaNitrogenBase;

import java.util.ArrayList;

public class DnaVerticalSequenceSort implements DnaSequenceSort {

    @Override
    public Dna sort(Dna dna) {
        var sequences = new ArrayList<DnaNitrogenBase[]>();

        var limit = (dna.nitrogenBases().size() % 2 == 0 ? dna.nitrogenBases().size() : dna.nitrogenBases().size() - 1);
        for (var row = 0; row < limit; row++) {

            var nitrogen = new DnaNitrogenBase[limit];
            for (var column = 0; column < limit; column++) {
                nitrogen[column] = dna.nitrogenBases().get(column)[row];
            }

            sequences.add(nitrogen);
        }

        return new Dna(sequences);
    }
}
