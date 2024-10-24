# Use an official OpenJDK runtime as a parent image
FROM openjdk:21-jdk-slim

# Set up working directory inside the container
WORKDIR /app

# Copy the Spring Boot jar file into the container
COPY target/*.jar easyhostel-backend.jar

# Expose the application port
EXPOSE 8080

# Run the Spring Boot app
ENTRYPOINT ["java", "-jar", "easyhostel-backend.jar"]