package br.com.meli.dna.analyzer.service;

import br.com.meli.dna.analyzer.api.exception.DnaAnalyzerRepositoryException;
import br.com.meli.dna.analyzer.business.objects.DnaSpecies;
import br.com.meli.dna.analyzer.model.DnaModel;
import br.com.meli.dna.analyzer.repository.DnaRepository;
import br.com.meli.dna.analyzer.view.DnaStatsView;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import java.util.Date;
import java.util.List;

@Service
public class DnaAnalyzerService {
    @Autowired
    private DnaRepository dnaRepository;

    public void save(List<String> dna, DnaSpecies species) {
        try {
            var dnsAsJson = new ObjectMapper().writeValueAsString(dna);

            dnaRepository.save(
                    new DnaModel(
                            DigestUtils.md5DigestAsHex(dnsAsJson.getBytes()),
                            dnsAsJson,
                            species,
                            new Date()
                    )
            );
        } catch (Exception e) {
            throw new DnaAnalyzerRepositoryException(e);
        }
    }

    public DnaStatsView getStats() {
        return dnaRepository.getStats();
    }
}
