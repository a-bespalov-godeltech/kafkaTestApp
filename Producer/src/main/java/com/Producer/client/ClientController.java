package com.Producer.client;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("clients")
@AllArgsConstructor
public class ClientController {

  private final ClientProducer clientManager;

  @PostMapping
  @ResponseStatus(value = HttpStatus.CREATED)
  public void create(@RequestBody Client client) {
    clientManager.create(client);
  }
}
