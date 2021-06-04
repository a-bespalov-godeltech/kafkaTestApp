FROM openjdk:8-jre-slim AS build
EXPOSE 8081
COPY --from=build /home/gradle/src/KafkaTestApp/Consumer/build/libs/*.jar /app/consumer.jar

ENTRYPOINT ["java", "-XX:+UnlockExperimentalVMOptions", "-jar","/app/consumer.jar"]
