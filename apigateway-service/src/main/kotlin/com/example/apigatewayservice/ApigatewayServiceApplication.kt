package com.example.apigatewayservice

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.cloud.client.discovery.EnableDiscoveryClient

@SpringBootApplication
@EnableDiscoveryClient
class ApigatewayServiceApplication

fun main(args: Array<String>) {
	runApplication<ApigatewayServiceApplication>(*args)
}
