package com.test.KafkaTestApp.client;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class ClientManager {

  public static final String CLIENT_KAFKA_TOPIC = "client";

  private final KafkaTemplate<String, Client> kafkaTemplate;

  public ClientManager(KafkaTemplate<String, Client> kafkaTemplate) {
    this.kafkaTemplate = kafkaTemplate;
  }

  public void create(Client client) {
    kafkaTemplate.send(CLIENT_KAFKA_TOPIC, client);
  }

  @KafkaListener(id = "KafkaListenerExample", topics = {CLIENT_KAFKA_TOPIC})
  public void consume(Client client) {
    System.out.println(client.getClientId() + " " + client.getEmail());
  }
}
