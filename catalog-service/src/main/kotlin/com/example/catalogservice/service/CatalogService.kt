package com.example.catalogservice.service

import com.example.catalogservice.dto.ResponseCatalog
import com.example.catalogservice.entity.CatalogRepository
import org.springframework.stereotype.Service

@Service
class CatalogService(
    val catalogRepository: CatalogRepository
) {

    fun getAllCatalogs(): List<ResponseCatalog>
        = catalogRepository.findAll().map { ResponseCatalog.of(it) }

}