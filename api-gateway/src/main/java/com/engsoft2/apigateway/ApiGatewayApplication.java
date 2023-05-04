package com.engsoft2.apigateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@RestController
@SpringBootApplication
public class ApiGatewayApplication {

	@Autowired
	private CircuitBreacker circuitBreacker;
  
	@RequestMapping("/currency-conversion")
	public Mono<String> conversionCurrency() {
	  return circuitBreacker.currencyConvertion();
	}

	public static void main(String[] args) {
		SpringApplication.run(ApiGatewayApplication.class, args);
	}

}
