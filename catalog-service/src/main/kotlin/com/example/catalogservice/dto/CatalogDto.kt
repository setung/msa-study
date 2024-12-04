package com.example.catalogservice.dto

data class CatalogDto(
    val productId: String,
    val qty: Int,
    val uintPrice: Int,
    val totalPrice: Int,

    val orderId: String,
    val userId: String
)
