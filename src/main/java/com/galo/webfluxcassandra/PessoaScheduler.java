package com.galo.webfluxcassandra;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;

import java.util.UUID;
import java.util.stream.IntStream;

@Component
public class PessoaScheduler {

  private final PessoaRepository repository;

  public PessoaScheduler(PessoaRepository repository) {
    this.repository = repository;
  }

  // Executa a cada 60 segundos
  @Scheduled(fixedRate = 60000)
  public void inserirMilPessoas() {
    Flux<Pessoa> pessoas = Flux.fromStream(
            IntStream.range(0, 1000).mapToObj(i -> {
              Pessoa p = new Pessoa();
              p.setId(UUID.randomUUID());
              p.setNome("Pessoa " + i);
              p.setCpf(String.format("%011d", i));
              return p;
            })
    );

    repository.saveAll(pessoas).subscribe();
    System.out.println("Inseridos 1000 registros no Cassandra.");
  }
}
