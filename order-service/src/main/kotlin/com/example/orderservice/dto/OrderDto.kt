package com.example.orderservice.dto

data class OrderDto(
    val productId: String?,
    val qty: Int?,
    val unitPrice: Int?,
    val totalPrice: Int?,

    val orderId: String?,
    var userId: String?
) {
}