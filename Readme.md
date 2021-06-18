#### Kafka Producer and Consumer example application

#### Usage:
##### 1. Build Docker images 

Maven:
```text
mvn -pl Producer spring-boot:build-image -Dspring-boot.build-image.imageName=test/kafka-producer-app
mvn -pl Consumer spring-boot:build-image -Dspring-boot.build-image.imageName=test/kafka-consumer-app
```
Gradle:
```text
gradlew :Producer:bootBuildImage --imageName=test/kafka-producer-app :Consumer:bootBuildImage --imageName=test/kafka-consumer-app
```

##### 2. Start project in Docker
```text
docker-compose up
```

https://spring.io/guides/gs/spring-boot-docker/
