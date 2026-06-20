package com.galo.webfluxcassandra;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class WebFluxCassandraApplication {

  public static void main(String[] args) {
    SpringApplication.run(WebFluxCassandraApplication.class, args);
  }

}
