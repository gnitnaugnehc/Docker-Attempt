# Use a base image with Java 17 installed
FROM openjdk:17-jdk

# Set the working directory inside the container
WORKDIR /app

# Copy the compiled JAR file into the container
COPY target/backend-0.0.1-SNAPSHOT.jar app.jar

# Expose the port that your Spring Boot application listens on
EXPOSE 8080

# Specify the command to run your application
CMD ["java", "-jar", "app.jar"]
