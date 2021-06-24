package com.consumer.client;


import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "clients")
public class ClientEntity implements Serializable {

  @Id
  Long id;

  String email;

  @Enumerated(EnumType.STRING)
  ClientStatus status;
}
