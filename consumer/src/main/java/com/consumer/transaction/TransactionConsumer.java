package com.consumer.transaction;

import static com.consumer.transaction.TransactionMapper.*;

import com.consumer.client.ClientEntity;
import com.consumer.client.ClientRepository;
import com.consumer.transaction.calc.ValueCalculationException;
import com.consumer.transaction.calc.ValueCalculator;
import com.transaction.Transaction;
import javax.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TransactionConsumer {

  private final ClientRepository clientRepository;
  private final TransactionRepository transactionRepository;
  private final ValueCalculator valueCalculator;
  private final Logger logger = LoggerFactory.getLogger(TransactionConsumer.class);

  @KafkaListener(
      id = "Transaction Kafka Listener",
      topics = "${spring.kafka.transaction-topic}",
      containerFactory = "transactionKafkaListenerContainerFactory")
  @Transactional
  public void consume(Transaction transaction) {

    var client = clientRepository.findById(transaction.getClientId())
        .orElseGet(() -> createClientMock(transaction.getClientId()));

    var entity = transactionToEntity(transaction, client);
    entity.setClient(client);

    try {
      entity.setValue(valueCalculator.calculate(transaction.getPrice(), transaction.getQuantity()));
    } catch (ValueCalculationException e) {
      logger.error(e.getMessage());
    }

    transactionRepository.save(entity);
  }

  private ClientEntity createClientMock(Long clientId) {
    return clientRepository.createMock(clientId);
  }
}
