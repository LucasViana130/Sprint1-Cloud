FROM eclipse-temurin:21-jdk

WORKDIR /app

COPY target/*.jar app.jar

RUN useradd -m springuser

RUN mkdir -p /app/data && chown -R springuser:springuser /app/data

USER springuser

ENTRYPOINT ["java", "-jar", "app.jar"]