package com.test.KafkaTestApp;

import com.test.KafkaTestApp.config.KafkaConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.kafka.annotation.EnableKafka;

@SpringBootApplication
@EnableKafka
@EnableConfigurationProperties(KafkaConfig.class)
public class KafkaTestAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(KafkaTestAppApplication.class, args);
	}
}
