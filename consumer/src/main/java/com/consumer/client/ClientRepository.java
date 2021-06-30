package com.consumer.client;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientRepository extends JpaRepository<ClientEntity, Long> {

  default ClientEntity createMock(Long clientId) {

    var mock = new ClientEntity();
    mock.setId(clientId);
    mock.setStatus(ClientStatus.MOCK);

    return save(mock);
  }
}
