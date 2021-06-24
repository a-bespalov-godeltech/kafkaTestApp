package com.producer.transaction;

import com.producer.config.KafkaConfig;
import com.transaction.Transaction;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TransactionProducer {

  private final KafkaTemplate<String, Transaction> kafkaTemplate;
  private final KafkaConfig kafkaConfig;

  public void create(Transaction transaction) {

    kafkaTemplate.send(kafkaConfig.getTransactionTopic(), transaction);
  }
}
