package com.consumer.config;

import com.client.Client;
import com.transaction.Transaction;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.boot.autoconfigure.kafka.KafkaProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.support.serializer.JsonDeserializer;

@Configuration
public class Config {

  @Bean
  public ConcurrentKafkaListenerContainerFactory<String, Client> clientKafkaListenerContainerFactory(KafkaProperties properties) {

    var consumerFactory = new DefaultKafkaConsumerFactory<>(
        properties.buildConsumerProperties(),
        new StringDeserializer(),
        new JsonDeserializer<>(Client.class)
    );

    var factory = new ConcurrentKafkaListenerContainerFactory<String, Client>();
    factory.setConsumerFactory(consumerFactory);
    return factory;
  }

  @Bean
  public ConcurrentKafkaListenerContainerFactory<String, Transaction> transactionKafkaListenerContainerFactory(KafkaProperties properties) {

    var consumerFactory = new DefaultKafkaConsumerFactory<>(
        properties.buildConsumerProperties(),
        new StringDeserializer(),
        new JsonDeserializer<>(Transaction.class)
    );

    var containerFactory = new ConcurrentKafkaListenerContainerFactory<String, Transaction>();
    containerFactory.setConsumerFactory(consumerFactory);
    return containerFactory;
  }
}
