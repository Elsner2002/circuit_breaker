package com.engsoft2.apigateway;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Mono;

import org.springframework.cloud.client.circuitbreaker.ReactiveCircuitBreaker;
import org.springframework.cloud.client.circuitbreaker.ReactiveCircuitBreakerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Service
public class CircuitBreacker {

  private static final Logger LOG = LoggerFactory.getLogger(CircuitBreacker.class);


  private final WebClient webClient;
  private final ReactiveCircuitBreaker circuitBreaker;

  public CircuitBreacker(ReactiveCircuitBreakerFactory circuitBreakerFactory) {
    this.webClient = WebClient.builder().baseUrl("http://localhost:8765").build();
    this.circuitBreaker = circuitBreakerFactory.create("recommended");
  }

  public Mono<String> currencyConvertion() {
    return readingListCircuitBreaker.run(webClient.get().uri("/recommended").retrieve().bodyToMono(String.class), throwable -> {
      LOG.warn("Error making request to conversion service", throwable);
      return Mono.just("Cloud Native Java (O'Reilly)");
    });
  }
}