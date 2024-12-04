package com.example.orderservice.controller

import com.example.orderservice.dto.OrderDto
import com.example.orderservice.dto.ResponseOrder
import com.example.orderservice.service.OrderService
import org.springframework.core.env.Environment
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/order-service")
class OrderController(
    val orderService: OrderService,
    val environment: Environment
) {

    @GetMapping("/health_check")
    fun status() =
        String.format("It's Working in catalog Service on PORT %s", environment.getProperty("local.server.port"))

    @PostMapping("/{userId}/orders")
    fun createOrder(@RequestBody order: OrderDto, @PathVariable userId: String): ResponseEntity<ResponseOrder> {
        order.userId = userId
        val createOrder = orderService.createOrder(order)
        return ResponseEntity(ResponseOrder.of(createOrder), HttpStatus.CREATED)
    }

    @GetMapping("/{userId}/orders")
    fun getOrder(@PathVariable userId: String) =
        ResponseEntity(orderService.findAllByUserId(userId).map { ResponseOrder.of(it) }, HttpStatus.OK)
}