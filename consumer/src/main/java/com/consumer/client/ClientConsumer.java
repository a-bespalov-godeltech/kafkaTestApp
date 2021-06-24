package com.consumer.client;

import static com.consumer.client.ClientMapper.*;

import com.client.Client;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ClientConsumer {

  private final ClientRepository repository;
  Logger logger = LoggerFactory.getLogger(ClientConsumer.class);

  @KafkaListener(
      id = "Client Kafka Listener",
      topics = "${spring.kafka.client-topic}",
      containerFactory = "clientKafkaListenerContainerFactory")
  public void consume(Client client) {
    logger.info(client.toString());

    repository.save(clientToEntity(client));
  }
}
