# Use an official OpenJDK runtime as the base image
FROM openjdk:17-jdk-slim-buster

# Set the working directory in the container
WORKDIR /app

# Copy the Spring Boot JAR file into the container
COPY target/expertsystem-0.0.1.jar app.jar

# Run the Spring Boot application when the container starts
CMD ["java", "-jar", "app.jar"]