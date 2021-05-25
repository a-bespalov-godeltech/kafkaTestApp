package com.test.KafkaTestApp.client;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Client {

  private final Long clientId;
  private final String email;

  public Client(
      @JsonProperty("clientId") Long clientId,
      @JsonProperty("email") String email
  ) {
    this.clientId = clientId;
    this.email = email;
  }

  public Long getClientId() {
    return clientId;
  }

  public String getEmail() {
    return email;
  }
}
