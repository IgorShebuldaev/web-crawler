FROM ubuntu:latest

ENV TZ=Europe/Minsk
RUN ln -snf /usr/share/zoneinfo/$TZ /etc/localtime && echo $TZ > /etc/timezone
RUN apt-get update
RUN apt-get install -y git

RUN git clone https://github.com/IgorShebuldaev/web-crawler.git /app

RUN cd app
RUN apt install -y openjdk-11-jdk
RUN apt install -y wget
RUN wget https://services.gradle.org/distributions/gradle-6.7-bin.zip
RUN apt install -y unzip
RUN unzip gradle-6.7-bin.zip
RUN mv gradle-6.7 /usr/local/gradle
ENV PATH=/usr/local/gradle/bin:$PATH

WORKDIR /app