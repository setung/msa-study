package com.example.catalogservice.entity

import jakarta.persistence.*
import org.springframework.data.annotation.CreatedDate
import org.springframework.data.jpa.domain.support.AuditingEntityListener
import java.time.LocalDate

@Entity
@EntityListeners(AuditingEntityListener::class)
class Catalog(

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null,
    val productId: String,
    val productName: String,
    var stock: Int,
    var unitPrice: Int,

    @CreatedDate
    @Column(updatable = false)
    val createdAt: LocalDate

) {
}