FROM alpine
RUN apk add --no-cache git

RUN rm -rf /home/kafkaTestApp \
    mkdir /home/kafkaTestApp \
           cd /home/kafkaTestApp \
           git clone https://github.com/a-bespalov-godeltech/kafkaTestApp.git
WORKDIR /home/kafkaTestApp

FROM gradle:6.9.0-jdk8 AS build
COPY --chown=gradle:gradle . /home/gradle/src
WORKDIR /home/gradle/src
RUN gradle build --no-daemon


FROM openjdk:8-jre-slim
EXPOSE 8080

RUN mkdir /app

COPY --from=build /home/gradle/src/build/libs/*.jar /app/spring-boot-application.jar

ENTRYPOINT ["java", "-XX:+UnlockExperimentalVMOptions", "-jar","/app/spring-boot-application.jar"]
