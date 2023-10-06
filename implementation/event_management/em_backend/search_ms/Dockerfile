FROM maven:3.9.2 as build
ENV HOME=/usr/app
RUN mkdir -p $HOME
WORKDIR $HOME
ADD . $HOME
RUN mvn clean package -D maven.test.skip=true

FROM openjdk:17
COPY --from=build /usr/app/target/search_ms-0.0.1-SNAPSHOT.jar /app/runner.jar
ENTRYPOINT java -jar /app/runner.jar
