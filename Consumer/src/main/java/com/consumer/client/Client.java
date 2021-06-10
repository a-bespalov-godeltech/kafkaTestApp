package com.consumer.client;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Value;

@Value
public class Client {

  Long clientId;
  String email;

  public Client(
      @JsonProperty("clientId") Long clientId,
      @JsonProperty("email") String email
  ) {
    this.clientId = clientId;
    this.email = email;
  }
}
