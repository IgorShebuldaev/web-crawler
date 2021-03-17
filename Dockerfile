FROM openjdk:12-alpine
COPY build/libs/webcrawler.jar /app/
WORKDIR /output
ENTRYPOINT ["java", "-jar", "/app/webcrawler.jar", "echo $@"]