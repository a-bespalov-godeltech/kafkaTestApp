package com.test.KafkaTestApp.client;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.kafka.test.context.EmbeddedKafka;
import org.springframework.test.annotation.DirtiesContext;

@SpringBootTest
@DirtiesContext
@EmbeddedKafka(
    partitions = 1,
    brokerProperties = {"listeners=PLAINTEXT://localhost:9092", "port=9092"})
class ClientManagerTest {

  @Autowired
  private ClientManager manager;

  @Test
  public void test() throws InterruptedException {

    manager.create(new Client(1L, "mail"));
    Thread.sleep(3000);

    List<Client> clients = manager.getClients().stream()
        .filter(c -> c.getClientId() == 1L)
        .collect(Collectors.toList());
    assertThat(clients).isEqualTo(Collections.singletonList(new Client(1L, "mail")));

  }
}
