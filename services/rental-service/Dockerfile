# Start with a base image containing Java runtime
FROM openjdk:15-jdk-alpine

# Add Maintainer Info
LABEL maintainer="your_email@example.com"

# Set a volume pointing to /tmp
VOLUME /tmp

# The application's jar file (ensure that this is the correct path and name of your jar file)
COPY target/rental-service-0.0.1-SNAPSHOT.jar app.jar

# Make port 8080 available to the world outside this container
EXPOSE 8080

# Run the jar file
ENTRYPOINT ["java","-jar","app.jar"]
