# DNA analyzer
Sistema para análise de DNA, indicando se a espécie é _Humana_ ou _Símia_.
A aplicação foi desenvolvida utilizando os recursos da JDK do *Java 16*, o framework *Spring Boot* 2.4.5, e o banco de dados em memória *H2 Embedded*.

## Requisitos
- [PDF com os requisitos do sistema](code-challenge-documentation.pdf)

## Como executar a aplicação local?
- Para execução local, compilar a aplicação e utilizar a ferramenta _docker-compose_, ou a JRE 16 do Java:
```
# docker-compose up
# java --enable-preview -jar target/dna-analyzer-1.0.0.jar
```
## Como executar a aplicação no ambiente produtivo?
- Para execução no ambiente produtivo, acessar os endereços:
    - https://dna-analyzer-t72wqommmq-uc.a.run.app
        - [/rest/health-check](https://dna-analyzer-t72wqommmq-uc.a.run.app/rest/health-check)
        - [/rest/dna/simian](https://dna-analyzer-t72wqommmq-uc.a.run.app/rest/dna/simian)
        - [/rest/dna/stats](https://dna-analyzer-t72wqommmq-uc.a.run.app/rest/dna/stats)
    
## APIs REST expostas pela aplicação
- Health check
    - GET
        - [/rest/health-check](https://dna-analyzer-t72wqommmq-uc.a.run.app/rest/health-check)
- Análise de DNA
    - POST
        - [/rest/dna/simian](https://dna-analyzer-t72wqommmq-uc.a.run.app/rest/dna/simian)
        - Conteúdo para envio
        ```
          Content-type: application/json
          {
              "dna": [
                  "CTGATA",
                  "TGACAC",
                  "GAGTGA",
                  "AGCGGT",
                  "GAATGA",
                  "ATGACC"
              ]
          }
      ```
- Estatística das análises processadas
    - GET
        - [/rest/dna/stats](https://dna-analyzer-t72wqommmq-uc.a.run.app/rest/dna/stats)