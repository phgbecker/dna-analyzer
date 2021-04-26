package br.com.meli.dna.analyzer.api.exception;

public class DnaAnalyzerRepositoryException extends RuntimeException {

    public DnaAnalyzerRepositoryException(Throwable cause) {
        super("Something wrong happened while saving the DNA", cause);
    }
}
