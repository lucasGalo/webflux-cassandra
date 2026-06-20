package com.galo.webfluxcassandra;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;

@Component
@Slf4j
public class PessoaSseScheduler {

  private final WebClient webClient;

  public PessoaSseScheduler(WebClient.Builder builder) {
    this.webClient = builder.baseUrl("http://localhost:8080").build();
  }

  // Executa a cada 60 segundos
  @Scheduled(fixedRate = 60000)
  public void consumirSse() {
    Flux<Pessoa> pessoasStream = webClient.get()
            .uri("/pessoas")
            .accept(org.springframework.http.MediaType.TEXT_EVENT_STREAM)
            .retrieve()
            .bodyToFlux(Pessoa.class);

    pessoasStream.subscribe(p ->
            log.info("Pessoa recebida via SSE: " + p.getNome() + " - CPF: " + p.getCpf())
    );
  }

  // Deleta uma pessoa específica (exemplo: primeira encontrada)
  @Scheduled(fixedRate = 70000)
  public void deletarUmaPessoa() {
    webClient.get()
            .uri("/pessoas")
            .retrieve()
            .bodyToFlux(Pessoa.class)
            .next() // pega apenas o primeiro
            .map(Pessoa::getId)
            .flatMap(id -> webClient.delete()
                    .uri("/pessoas/{id}", id)
                    .retrieve()
                    .bodyToMono(Void.class))
            .subscribe(v -> log.info("Pessoa deletada via HTTP"));
  }

  // Deleta os 1000 primeiros registros encontrados a cada 5 minutos
  @Scheduled(fixedRate = 30000)
  public void deletarAntigos() {
    log.info("START");
    webClient.get()
            .uri("/pessoas")
            .retrieve()
            .bodyToFlux(Pessoa.class)
            .take(1000) // pega apenas os 1000 primeiros
            .map(Pessoa::getId)
            .flatMap(id -> webClient.delete()
                    .uri("/pessoas/{id}", id)
                    .retrieve()
                    .bodyToMono(Void.class))
            .subscribe(v -> log.info("Pessoa antiga deletada via HTTP"));
    log.info("END");
  }
}
