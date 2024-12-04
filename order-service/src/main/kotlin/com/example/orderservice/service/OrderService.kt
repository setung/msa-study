package com.example.orderservice.service

import com.example.orderservice.dto.OrderDto
import com.example.orderservice.jpa.Order
import com.example.orderservice.jpa.OrderRepository
import org.springframework.stereotype.Service

@Service
class OrderService(
    val orderRepository: OrderRepository
) {


    fun createOrder(orderDto: OrderDto): Order {
        val order = Order.of(orderDto)
        orderRepository.save(order)
        return order
    }

    fun findAllByUserId(userId: String) = orderRepository.findByUserId(userId)

    fun getOrderByOrderId(orderId: String) = orderRepository.findByOrderId(orderId) ?: throw NullPointerException()

}