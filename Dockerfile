# Use Eclipse Temurin 17 as base image
FROM eclipse-temurin:17-jdk-alpine

# Set working directory
WORKDIR /app

# Copy Maven wrapper and pom.xml
COPY mvnw .
COPY .mvn .mvn
COPY pom.xml .


# Copy module sources
COPY student-module ./student-module
COPY teacher-module ./teacher-module
COPY staff-module ./staff-module
COPY security-module ./security-module

# Make mvnw executable
RUN chmod +x mvnw

# Build the application
RUN ./mvnw clean package -DskipTests

# Expose port
EXPOSE 8080

# Change this to the absolute path of the staff-module JAR
CMD ["java", "-jar", "/app/staff-module/target/staff-module-0.0.1-SNAPSHOT.jar"]

# Run the application
#CMD ["java", "-jar", "target/*.jar"]
# The one with *.jar will not run since Docker cannot expand * wildcard, we must specify like "target/nova-university-0.0.1-SNAPSHOT.jar"
