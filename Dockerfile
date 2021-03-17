FROM gradle:6.8.3-jdk11
COPY . /src
WORKDIR /src
RUN gradle jar
ENTRYPOINT ["java", "-jar", "/src/build/libs/webcrawler-1.0.jar", "echo $@"]