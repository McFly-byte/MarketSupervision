FROM openjdk:17-jdk-slim
LABEL authors="lmc"
WORKDIR /app
COPY /target/MarketSupervision-0.0.1-SNAPSHOT.jar /app/app.jar
EXPOSE 8000
ENTRYPOINT ["java", "-jar", "/app/app.jar"]