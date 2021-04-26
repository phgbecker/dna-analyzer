package br.com.meli.dna.analyzer.repository;

import br.com.meli.dna.analyzer.model.DnaModel;
import br.com.meli.dna.analyzer.view.DnaStatsView;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface DnaRepository extends JpaRepository<DnaModel, String> {

    @Query(value =
            " SELECT                                                            " +
            "   COUNT(CASE WHEN species = 'HUMAN' THEN 1 END) humans,           " +
            "   COUNT(CASE WHEN species = 'SIMIAN' THEN 1 END) simians,         " +
            "   COALESCE(                                                       " +
            "     CAST(SUM(CASE WHEN species = 'SIMIAN' THEN 1 END) AS DECIMAL) " +
            "     /                                                             " +
            "     CAST(SUM(CASE WHEN species = 'HUMAN' THEN 1 END) AS DECIMAL)  " +
            "   , 0) ratio " +
            " FROM dna  ",
            nativeQuery = true)
    DnaStatsView getStats();
}
