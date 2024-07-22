FROM docker.io/library/openjdk:17-oracle
ENV DB_URL jdbc:postgresql://postgresql:5432/postgres
ENV DB_USERNAME postgres
ENV DB_PASSWORD postgres
WORKDIR /app
COPY target/filelister-0.0.1-SNAPSHOT.jar app.jar
EXPOSE 8080
CMD ["java", "-Dserver.port=8080", "-jar", "app.jar", \
    "--spring.datasource.url=${DB_URL}", \
    "--spring.datasource.username=${DB_USERNAME}", \
    "--spring.datasource.password=${DB_PASSWORD}"]