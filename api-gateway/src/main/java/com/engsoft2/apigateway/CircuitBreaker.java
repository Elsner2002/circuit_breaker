package com.engsoft2.apigateway;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Mono;

import org.apache.hc.coreS.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class CircuitBreaker {

  @RequestMapping("open-circuit-breaker")
  public ResponseEntity<String> handleFallback(){
      String error = "Process off while processing your request.";
      return ResponseEntity.status(HttpStatus.SC_INTERNAL_SERVER_ERROR).body(error);
  }
}