package br.com.meli.dna.analyzer.business.sort;

import br.com.meli.dna.analyzer.business.objects.Dna;
import br.com.meli.dna.analyzer.business.objects.DnaNitrogenBase;

import java.util.ArrayList;

public class DnaDiagonalForwardSequenceSort implements DnaSequenceSort {

    @Override
    public Dna sort(Dna dna) {
        var sequences = new ArrayList<DnaNitrogenBase[]>();

        var limit = dna.nitrogenBases().size();
        var diagonalRowIndex = 0;
        for (var column = 0; column <= (limit - 4); column++) {

            var nitrogenBases = new DnaNitrogenBase[limit];
            var innerColumnIndex = 0;
            for (var row = diagonalRowIndex; row < limit; row++) {
                nitrogenBases[row] = dna.nitrogenBases().get(innerColumnIndex++)[row];
            }

            diagonalRowIndex++;
            sequences.add(nitrogenBases);
        }

        return new Dna(sequences);
    }
}
