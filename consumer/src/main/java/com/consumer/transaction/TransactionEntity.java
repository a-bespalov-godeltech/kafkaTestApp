package com.consumer.transaction;

import com.consumer.client.ClientEntity;
import com.transaction.TransactionType;
import java.io.Serializable;
import java.time.LocalDateTime;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "transaction")
public class TransactionEntity implements Serializable {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  Long id;

  String bank;

  @ManyToOne(fetch = FetchType.LAZY)
  ClientEntity client;

  @Enumerated(EnumType.STRING)
  TransactionType transactionType;

  Integer quantity;

  Double price;

  LocalDateTime createdAt;
}
