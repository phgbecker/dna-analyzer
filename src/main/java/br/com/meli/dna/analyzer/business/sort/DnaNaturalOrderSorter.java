package br.com.meli.dna.analyzer.business.sort;

import br.com.meli.dna.analyzer.business.objects.Dna;

public class DnaNaturalOrderSorter implements DnaSequenceSort {

    @Override
    public Dna sort(Dna dna) {
        return dna;
    }
}
