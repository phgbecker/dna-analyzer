package br.com.meli.dna.analyzer.api.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public record DnaStatsDTO(@JsonProperty("count_human_dna") Integer humans,
                          @JsonProperty("count_simian_dna") Integer simians,
                          @JsonProperty("ratio") Double ratio) {
}
