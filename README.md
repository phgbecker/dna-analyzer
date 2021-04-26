# DNA analyzer
Sistema para análise de DNA, indicando se a espécie é _Humana_ ou _Símia_.
A aplicação foi desenvolvida utilizando os recursos da JDK do *Java 16* e o framework *Spring Boot* 2.4.5.

## Requisitos
- [PDF com os requisitos do sistema](code-challenge-documentation.pdf)

## Como executar a aplicação?
- Para execução local, utilizar a ferramenta _docker-compose_
```
docker-compose up
```
- Ou compilar a aplicação e executar manualmente utilizando uma JRE 16
```
java --enable-preview -jar target/dna-analyzer-1.0.0.jar
```

## APIs REST expostas pela aplicação
- Requisição REST para as rotas
    - Health check
        - GET
            - /rest/health-check
    - Análise de DNA
        - POST
            - /rest/dna/simian
    - Estatística das análises processadas
        - GET
            - /rest/dna/stats