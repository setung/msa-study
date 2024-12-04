package com.example.orderservice.jpa

import org.springframework.data.jpa.repository.JpaRepository

interface OrderRepository : JpaRepository<Order, Long> {

    fun findByOrderId(orderId: String): Order?

    fun findByUserId(userId: String): List<Order>
}