#FROM openjdk:17-jdk-alpine
#WORKDIR /app
#COPY target/storypath_backend-0.0.1-SNAPSHOT.jar /app/storypath-backend.jar
#EXPOSE 8080
#CMD [ "java", "-jar","storypath-backend.jar" ]



# Stage 1: Build the application
FROM maven:3.8.6-openjdk-17-slim AS build
WORKDIR /app
COPY pom.xml .
COPY src ./src
RUN mvn clean package -DskipTests

# Stage 2: Run the application using a lightweight runtime image
FROM openjdk:17-jre-alpine
WORKDIR /app
COPY --from=build /app/target/storypath_backend-0.0.1-SNAPSHOT.jar /app/storypath-backend.jar
EXPOSE 8080
CMD ["java", "-jar", "storypath-backend.jar"]
