FROM eclipse-temurin:25-jdk-jammy
WORKDIR /app
COPY target/url-shortner-4.0.5.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]