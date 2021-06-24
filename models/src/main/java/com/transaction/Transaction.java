package com.transaction;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.time.LocalDateTime;
import lombok.Value;

@Value
public class Transaction {

  String bank;
  Long clientId;
  TransactionType transactionType;
  Integer quantity;
  Double price;
  @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
  LocalDateTime createdAt;
}
