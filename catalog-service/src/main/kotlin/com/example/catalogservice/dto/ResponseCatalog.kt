package com.example.catalogservice.dto

import com.example.catalogservice.entity.Catalog
import java.time.LocalDate

data class ResponseCatalog(
    val productId: String,
    val productName: String,
    val stock: Int,
    val uintPrice: Int,
    val totalPrice: Int,
    val createdAt: LocalDate?
) {
    companion object {
        fun of(catalog: Catalog) = ResponseCatalog(
            productId = catalog.productId,
            productName = catalog.productName,
            stock = catalog.stock,
            uintPrice = catalog.unitPrice,
            totalPrice = catalog.unitPrice,
            createdAt = catalog.createdAt,
        )
    }
}
