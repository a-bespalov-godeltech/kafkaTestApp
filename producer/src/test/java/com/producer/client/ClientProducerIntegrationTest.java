package com.producer.client;

import static org.assertj.core.api.Assertions.assertThat;

import com.client.Client;
import com.producer.config.KafkaConfig;
import java.util.HashMap;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;
import lombok.SneakyThrows;
import lombok.val;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
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
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class ClientProducerIntegrationTest {

  BlockingQueue<ConsumerRecord<String, Client>> records;
  KafkaMessageListenerContainer<String, Client> container;

  @Autowired
  private ClientProducer producer;

  @Autowired
  private KafkaConfig kafkaConfig;

  @Autowired
  private EmbeddedKafkaBroker embeddedKafkaBroker;

  @BeforeAll
  void setUp() {
    val configs = new HashMap<>(KafkaTestUtils.consumerProps("consumer", "false", embeddedKafkaBroker));
    val consumerFactory = new DefaultKafkaConsumerFactory<>(configs, new StringDeserializer(), new JsonDeserializer<>(Client.class));
    val containerProperties = new ContainerProperties(kafkaConfig.getClientTopic());
    container = new KafkaMessageListenerContainer<>(consumerFactory, containerProperties);
    records = new LinkedBlockingQueue<>();
    container.setupMessageListener((MessageListener<String, Client>) records::add);
    container.start();
    ContainerTestUtils.waitForAssignment(container, embeddedKafkaBroker.getPartitionsPerTopic());
  }

  @AfterAll
  void tearDown() {
    container.stop();
  }

  @Test
  @SneakyThrows
  void clientRecordShouldSendToKafka() {

    Client client = new Client(1L, "client@mail");
    producer.create(client);

    ConsumerRecord<String, Client> clientRecord = records.poll(5, TimeUnit.SECONDS);
    assertThat(clientRecord).isNotNull();

    final Client clientRecordValue = clientRecord.value();
    assertThat(clientRecordValue).isEqualTo(client);
  }
}
