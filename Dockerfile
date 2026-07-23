
FROM eclipse-temurin:25-jdk-jammy
ADD ./target/urlshortener-4.0.5.jar urlshortenerapp.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "urlshortenerapp.jar"]