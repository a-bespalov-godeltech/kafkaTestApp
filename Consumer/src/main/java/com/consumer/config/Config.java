package com.consumer.config;

import com.consumer.client.Client;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.boot.autoconfigure.kafka.KafkaProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.support.serializer.JsonDeserializer;

@Configuration
public class Config {

  @Bean
  public ConsumerFactory<String, Client> consumerFactory(KafkaProperties properties) {
    return new DefaultKafkaConsumerFactory<>(
        properties.buildConsumerProperties(),
        new StringDeserializer(),
        new JsonDeserializer<>(Client.class)
        );
  }

  @Bean
  public ConcurrentKafkaListenerContainerFactory<String, Client> kafkaListenerContainerFactory(ConsumerFactory<String, Client> consumerFactory) {

    ConcurrentKafkaListenerContainerFactory<String, Client> factory = new ConcurrentKafkaListenerContainerFactory<>();
    factory.setConsumerFactory(consumerFactory);
    return factory;
  }
}
