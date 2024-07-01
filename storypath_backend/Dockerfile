FROM openjdk:17-jdk-alpine
WORKDIR /app
COPY target/storypath_backend-0.0.1-SNAPSHOT.jar /app/storypath-backend.jar
EXPOSE 8080
CMD [ "java", "-jar","storypath-backend.jar" ]