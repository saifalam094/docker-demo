# Use JDK 17
FROM eclipse-temurin:17-jdk
LABEL maintainer="saifalam.in094@gmail.com"

# Set working directory
WORKDIR /app

# Copy the already-built JAR
COPY target/docker-demo-0.0.1-SNAPSHOT.jar docker-demo.jar

# Expose port
EXPOSE 8080

# Run the application
ENTRYPOINT ["java", "-jar", "docker-demo.jar"]
