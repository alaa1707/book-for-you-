# Use a base image with Java 20
FROM openjdk:20-jdk-slim
  
  # Set the working directory
WORKDIR /app
  
  # Copy the built JAR file into the container
COPY target/*.jar app.jar
  
  # Expose the port your application is listening on
EXPOSE 9081
  
  # Set the command to run your application
CMD ["java", "-jar", "app.jar"]
