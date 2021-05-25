package com.test.KafkaTestApp.config;

import static com.test.KafkaTestApp.client.ClientManager.CLIENT_KAFKA_TOPIC;

import com.test.KafkaTestApp.client.Client;
import org.springframework.boot.autoconfigure.kafka.KafkaProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaAdmin.NewTopics;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;

@Configuration
public class Config {

  @Bean
  public ConsumerFactory<String, Client> consumerFactory(KafkaProperties properties) {
    return new DefaultKafkaConsumerFactory<>(properties.buildConsumerProperties());
  }

  @Bean
  public ProducerFactory<String, Client> clientProducerFactory(KafkaProperties properties) {
    return new DefaultKafkaProducerFactory<>(properties.buildProducerProperties());
  }

  @Bean
  public KafkaTemplate<String, Client> clientTemplate(ProducerFactory<String, Client> factory) {
    return new KafkaTemplate<>(factory);
  }

  @Bean
  public NewTopics topics() {
    return new NewTopics(
        TopicBuilder
            .name(CLIENT_KAFKA_TOPIC)
            .build());
  }
}
