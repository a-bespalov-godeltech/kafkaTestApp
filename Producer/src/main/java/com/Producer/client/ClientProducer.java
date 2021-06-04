package com.Producer.client;

import com.Producer.config.KafkaConfig;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ClientProducer {

  private final KafkaTemplate<String, Client> kafkaTemplate;
  private final KafkaConfig kafkaConfig;

  public void create(Client client) {
    kafkaTemplate.send(kafkaConfig.getClientTopic(), client);
  }
}
