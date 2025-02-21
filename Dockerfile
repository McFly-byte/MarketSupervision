FROM openjdk:17-jdk
LABEL authors="lmc"
RUN apt-get update && apt-get install -y fontconfig fonts-wqy-microhei fonts-dejavu && rm -rf /var/lib/apt/lists/*
WORKDIR /app
COPY /target/MarketSupervision-0.0.1-SNAPSHOT.jar /app/app.jar
EXPOSE 8000
ENTRYPOINT ["java", "-Djava.awt.headless=true", "-jar", "/app/app.jar"]