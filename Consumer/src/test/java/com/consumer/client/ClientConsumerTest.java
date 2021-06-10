package com.consumer.client;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.stream.Collectors;
import lombok.val;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.listener.ContainerProperties;
import org.springframework.kafka.listener.KafkaMessageListenerContainer;
import org.springframework.kafka.listener.MessageListener;
import org.springframework.kafka.support.serializer.JsonDeserializer;
import org.springframework.kafka.test.EmbeddedKafkaBroker;
import org.springframework.kafka.test.context.EmbeddedKafka;
import org.springframework.kafka.test.utils.ContainerTestUtils;
import org.springframework.kafka.test.utils.KafkaTestUtils;
import org.springframework.test.annotation.DirtiesContext;

@SpringBootTest
@DirtiesContext
@EmbeddedKafka(
    partitions = 1,
    brokerProperties = {"listeners=PLAINTEXT://localhost:9092", "port=9092"})
class ClientConsumerTest {

  BlockingQueue<ConsumerRecord<String, Client>> records;
  KafkaMessageListenerContainer<String, Client> container;
  @Autowired
  private KafkaTemplate<String, String> kafkaTemplate;
  @Autowired
  private ClientConsumer consumer;
  @Autowired
  private EmbeddedKafkaBroker embeddedKafkaBroker;

  @BeforeEach
  void setUp() {
    val configs = new HashMap<>(
        KafkaTestUtils.consumerProps("consumer", "false", embeddedKafkaBroker));
    val consumerFactory = new DefaultKafkaConsumerFactory<>(configs, new StringDeserializer(),
        new JsonDeserializer<>(Client.class));
    val containerProperties = new ContainerProperties("client");
    container = new KafkaMessageListenerContainer<>(consumerFactory, containerProperties);
    records = new LinkedBlockingQueue<>();
    container.setupMessageListener((MessageListener<String, Client>) records::add);
    container.start();
    ContainerTestUtils.waitForAssignment(container, embeddedKafkaBroker.getPartitionsPerTopic());
  }

  @AfterEach
  void tearDown() {
    container.stop();
  }

  @Test
  public void test() throws InterruptedException, ExecutionException {

    // raw JSON just to not over-engineering in this test
    kafkaTemplate.send("client", "{\"clientId\": 1, \"email\": \"mail\"}");
    Thread.sleep(2000);

    List<Client> clients = consumer.getClients().stream()
        .filter(c -> c.getClientId() == 1L)
        .collect(Collectors.toList());
    assertThat(clients).isEqualTo(Collections.singletonList(new Client(1L, "mail")));

  }
}
