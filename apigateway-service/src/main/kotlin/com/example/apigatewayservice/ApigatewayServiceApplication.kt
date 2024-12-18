package com.example.apigatewayservice

import org.springframework.boot.actuate.web.exchanges.HttpExchangeRepository
import org.springframework.boot.actuate.web.exchanges.InMemoryHttpExchangeRepository
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.cloud.client.discovery.EnableDiscoveryClient
import org.springframework.context.annotation.Bean

@SpringBootApplication
@EnableDiscoveryClient
class ApigatewayServiceApplication

fun main(args: Array<String>) {
	runApplication<ApigatewayServiceApplication>(*args)
}

@Bean
fun httpTraceRepository(): HttpExchangeRepository {
	return InMemoryHttpExchangeRepository()
}