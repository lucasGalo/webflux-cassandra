package com.galo.webfluxcassandra;

import org.springframework.data.cassandra.repository.ReactiveCassandraRepository;
import java.util.UUID;

public interface PessoaRepository extends ReactiveCassandraRepository<Pessoa, UUID> {
}
