package com.producer.config;

import com.client.Client;
import com.transaction.Transaction;
import org.springframework.boot.autoconfigure.kafka.KafkaProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;

@Configuration
public class Config {

  @Bean
  public ProducerFactory<String, Client> clientProducerFactory(KafkaProperties properties) {
    return new DefaultKafkaProducerFactory<>(properties.buildProducerProperties());
  }

  @Bean
  public KafkaTemplate<String, Client> clientTemplate(ProducerFactory<String, Client> factory) {
    return new KafkaTemplate<>(factory);
  }


  @Bean
  public ProducerFactory<String, Transaction> transactionProducerFactory(KafkaProperties properties) {
    return new DefaultKafkaProducerFactory<>(properties.buildProducerProperties());
  }

  @Bean
  public KafkaTemplate<String, Transaction> transactionTemplate(ProducerFactory<String, Transaction> factory) {
    return new KafkaTemplate<>(factory);
  }
}
