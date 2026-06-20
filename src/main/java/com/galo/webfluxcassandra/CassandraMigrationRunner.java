package com.galo.webfluxcassandra;

import com.contrastsecurity.cassandra.migration.CassandraMigration;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

//@Component
public class CassandraMigrationRunner implements CommandLineRunner {

  @Override
  public void run(String... args) {
    CassandraMigration migration = new CassandraMigration();
    migration.migrate();
  }
}
