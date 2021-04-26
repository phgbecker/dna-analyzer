FROM openjdk:16-slim

ENV ARTIFACT_NAME dna-analyzer-1.0.0

COPY ./target/$ARTIFACT_NAME.jar $ARTIFACT_NAME.jar

EXPOSE 8080

CMD java --enable-preview -jar -server $ARTIFACT_NAME.jar
