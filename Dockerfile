# Stage 1: Build with Maven
FROM maven:3.9.3-eclipse-temurin-17 AS build
WORKDIR /app
COPY pom.xml .
COPY src ./src
RUN mvn clean package -DskipTests

# Stage 2: Run with JDK
FROM eclipse-temurin:17-jdk
WORKDIR /app
COPY --from=build /app/target/docker-demo-0.0.1-SNAPSHOT.jar docker-demo.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "docker-demo.jar"]
