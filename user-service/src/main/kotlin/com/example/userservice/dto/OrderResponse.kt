package com.example.userservice.dto

import java.time.LocalDate

data class OrderResponse(
    val productId: String,
    val qty: Int,
    val unitPrice: Int,
    val totalPrice: Int,
    val createdAt: LocalDate,
    val orderId: String,
)
