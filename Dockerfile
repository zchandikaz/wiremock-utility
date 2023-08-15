# Use an official Maven image as the build environment
FROM maven:3.8.3-amazoncorretto-17 AS build

WORKDIR /app

# Copy the source code and pom.xml to the container
COPY src /app/src
COPY pom.xml /app

# Build the JAR file
RUN mvn clean package

# Use a smaller base image for the runtime environment
FROM amazoncorretto:17

WORKDIR /app

# Copy the JAR file from the build image
COPY --from=build /app/target/WireMockUtility.jar /app/app.jar

# Start the application
CMD ["java", "-jar", "app.jar"]
