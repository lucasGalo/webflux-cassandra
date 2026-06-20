package com.galo.webfluxcassandra;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.UUID;

@Slf4j
@RestController
@RequestMapping("/pessoas")
public class PessoaController {

  private final PessoaRepository repository;

  public PessoaController(PessoaRepository repository) {
    this.repository = repository;
  }

  @GetMapping(produces = MediaType.TEXT_EVENT_STREAM_VALUE)  public Flux<Pessoa> listar() {
    return repository.findAll();
  }

  @PostMapping
  public Mono<Pessoa> salvar(@RequestBody Pessoa pessoa) {
    return repository.save(pessoa);
  }

  @DeleteMapping("/{id}")
  public Mono<Void> deletarPorId(@PathVariable UUID id) {
    log.info("Deletando por ID: " + id);
    return repository.deleteById(id);
  }
}
