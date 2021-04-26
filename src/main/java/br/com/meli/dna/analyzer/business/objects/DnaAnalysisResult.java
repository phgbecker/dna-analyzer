package br.com.meli.dna.analyzer.business.objects;

public sealed interface DnaAnalysisResult {
    record Success(DnaSpecies species) implements DnaAnalysisResult {}
    record Failure(String error) implements DnaAnalysisResult {}
}
