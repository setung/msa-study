package com.example.catalogservice.controller

import com.example.catalogservice.service.CatalogService
import org.springframework.core.env.Environment
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/catalog-service")
class CatalogController(
    val catalogService: CatalogService,
    val environment: Environment
) {

    @GetMapping("/health_check")
    fun status() =
        String.format("It's Working in catalog Service on PORT %s", environment.getProperty("local.server.port"))

    @GetMapping("/catalogs")
    fun getUsers() = ResponseEntity(catalogService.getAllCatalogs(), HttpStatus.OK)
}