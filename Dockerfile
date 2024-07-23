FROM docker.io/library/gradle:8.9.0-jdk17-alpine AS build
WORKDIR /build
COPY . .
RUN gradle clean javadoc && gradle build

FROM docker.io/library/openjdk:17-oracle
RUN groupadd -r user1 && useradd -r -g user1 user1
RUN groupadd -r user2 && useradd -r -g user2 user2
ENV DB_URL jdbc:postgresql://postgresql:5432/postgres
ENV DB_USERNAME postgres
ENV DB_PASSWORD postgres
WORKDIR /app
COPY --from=build /build/build/libs/filelister-0.0.1-SNAPSHOT.jar app.jar
EXPOSE 8080
CMD ["java", "-Dserver.port=8080", "-jar", "app.jar", \
    "--spring.datasource.url=${DB_URL}", \
    "--spring.datasource.username=${DB_USERNAME}", \
    "--spring.datasource.password=${DB_PASSWORD}"]