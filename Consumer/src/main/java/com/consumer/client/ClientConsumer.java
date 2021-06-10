package com.consumer.client;

import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ClientConsumer {

  private final List<Client> clients = new ArrayList<>();

  @KafkaListener(id = "KafkaListenerExample", topics = "${spring.kafka.client-topic}")
  public void consume(Client client) {
    System.out.println(client.getClientId() + " " + client.getEmail());
    clients.add(client);
  }

  public List<Client> getClients() {
    return clients;
  }
}
