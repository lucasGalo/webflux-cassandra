package com.galo.webfluxcassandra;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.cassandra.core.mapping.Table;
import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Getter @Setter
@Table("pessoa")
public class Pessoa {
  @Id
  private UUID id;
  private String nome;
  private String cpf;
}
