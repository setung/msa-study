package com.example.orderservice.jpa

import com.example.orderservice.dto.OrderDto
import jakarta.persistence.*
import org.springframework.data.annotation.CreatedDate
import java.time.LocalDate
import java.util.*

@Entity
@Table(name = "orders")
class Order(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null,
    val productId: String,
    val qty: Int,
    var totalPrice: Int,
    var unitPrice: Int,

    var userId: String,

    var orderId: String,

    @CreatedDate
    @Column(updatable = false)
    val createdAt: LocalDate?
) {
    companion object {
        fun of(orderDto: OrderDto) = Order(
            id = null,
            productId = orderDto.productId!!,
            qty = orderDto.qty!!,
            unitPrice = orderDto.unitPrice!!,
            totalPrice = orderDto.qty * orderDto.unitPrice,
            userId = orderDto.userId!!,
            orderId = UUID.randomUUID().toString(),
            createdAt = LocalDate.now()
        )
    }
}