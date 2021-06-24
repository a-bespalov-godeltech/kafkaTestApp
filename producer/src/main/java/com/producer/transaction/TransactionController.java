package com.producer.transaction;

import com.transaction.Transaction;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("transactions")
@AllArgsConstructor
public class TransactionController {

  private final TransactionProducer transactionProducer;

  @PostMapping
  @ResponseStatus(value = HttpStatus.CREATED)
  public void create(@RequestBody Transaction transaction) {
    transactionProducer.create(transaction);
  }
}
