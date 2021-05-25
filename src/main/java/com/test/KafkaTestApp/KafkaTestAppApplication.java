package com.test.KafkaTestApp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.kafka.annotation.EnableKafka;

@SpringBootApplication
@EnableKafka
public class KafkaTestAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(KafkaTestAppApplication.class, args);
	}
}
