package com.consumer.transaction;

import com.consumer.client.ClientEntity;
import com.transaction.Transaction;
import lombok.experimental.UtilityClass;

@UtilityClass
public class TransactionMapper {

  TransactionEntity transactionToEntity(Transaction transaction, ClientEntity clientEntity) {

    var entity = new TransactionEntity();

    entity.setBank(transaction.getBank());
    entity.setClient(clientEntity);
    entity.setTransactionType(transaction.getTransactionType());
    entity.setQuantity(transaction.getQuantity());
    entity.setPrice(transaction.getPrice());
    entity.setCreatedAt(transaction.getCreatedAt());

    return entity;
  }
}
