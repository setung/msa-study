package com.example.orderservice.dto

import com.example.orderservice.jpa.Order
import java.time.LocalDate

data class ResponseOrder(
    val productId: String,
    val qty: Int,
    val uintPrice: Int,
    val totalPrice: Int,
    val createdAt: LocalDate?,

    val orderId: String
) {
    companion object {
        fun of(order: Order) = ResponseOrder(
            productId = order.productId,
            uintPrice = order.unitPrice,
            totalPrice = order.totalPrice,
            createdAt = order.createdAt,
            qty = order.qty,
            orderId = order.orderId
        )
    }
}
