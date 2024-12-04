package com.example.catalogservice.entity

import org.springframework.data.jpa.repository.JpaRepository

interface CatalogRepository : JpaRepository<Catalog, Long> {

    fun findByProductId(productId: String): Catalog
}