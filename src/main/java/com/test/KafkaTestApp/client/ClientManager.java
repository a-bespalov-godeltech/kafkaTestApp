package com.test.KafkaTestApp.client;

import com.test.KafkaTestApp.config.KafkaConfig;
import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ClientManager {

  private final KafkaTemplate<String, Client> kafkaTemplate;
  private final KafkaConfig kafkaConfig;
  private List<Client> clients = new ArrayList<>();

  public void create(Client client) {
    kafkaTemplate.send(kafkaConfig.getClientTopic(), client);
  }

  @KafkaListener(id = "KafkaListenerExample", topics = "${spring.kafka.client-topic}")
  public void consume(Client client) {
    System.out.println(client.getClientId() + " " + client.getEmail());
    clients.add(client);
  }

  public List<Client> getClients() {
    return clients;
  }
}
