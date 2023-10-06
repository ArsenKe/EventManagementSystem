FROM maven:3.9.2 as base
ENV HOME=/usr/app
RUN mkdir -p $HOME
WORKDIR $HOME
ADD . $HOME
ADD pom.xml $HOME

FROM base as test
ENV SPRING_DATASOURCE_URL=jdbc:mysql://host.docker.internal:3306/em_db
ENV SPRING_DATASOURCE_USERNAME=root
ENV SPRING_DATASOURCE_PASSWORD=password
RUN mvn -f attendance_ms/pom.xml clean verify

FROM base as build
RUN mvn clean package -D maven.test.skip=true

FROM openjdk:17
COPY --from=build /usr/app/target/attendance_ms-0.0.1-SNAPSHOT.jar /app/runner.jar
ENTRYPOINT java -jar /app/runner.jar
