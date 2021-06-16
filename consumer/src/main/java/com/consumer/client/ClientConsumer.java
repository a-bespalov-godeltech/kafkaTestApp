package com.consumer.client;

import com.client.Client;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ClientConsumer {

  Logger logger = LoggerFactory.getLogger(ClientConsumer.class);

  @KafkaListener(id = "KafkaListenerExample", topics = "${spring.kafka.client-topic}")
  public void consume(Client client) {
    logger.info(client.toString());
  }
}
