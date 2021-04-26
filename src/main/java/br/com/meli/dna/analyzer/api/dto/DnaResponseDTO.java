package br.com.meli.dna.analyzer.api.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public record DnaResponseDTO(@JsonProperty("is_simian") Boolean isSimian) {
}
