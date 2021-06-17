package com.consumer.client;

import com.client.Client;
import lombok.experimental.UtilityClass;

/*
  for mapping from DTO to entity and vice versa etc could be used several ways:
  same class, libraries, utility functions
 */

@UtilityClass
class ClientMapper {

  ClientEntity clientToEntity(Client client) {
    var entity = new ClientEntity();
    entity.setId(client.getClientId());
    entity.setEmail(client.getEmail());
    return entity;
  }

}
