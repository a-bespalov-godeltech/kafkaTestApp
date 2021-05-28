package com.test.KafkaTestApp.client;

import com.test.KafkaTestApp.config.KafkaConfig;
import lombok.AllArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ClientManager {

  private final KafkaTemplate<String, Client> kafkaTemplate;
  private final KafkaConfig kafkaConfig;

  public void create(Client client) {
    kafkaTemplate.send(kafkaConfig.getClientTopic(), client);
  }

  @KafkaListener(id = "KafkaListenerExample", topics = {"#{'${spring.kafka.client-topic}'}"})
  public void consume(Client client) {
    System.out.println(client.getClientId() + " " + client.getEmail());
  }
}
