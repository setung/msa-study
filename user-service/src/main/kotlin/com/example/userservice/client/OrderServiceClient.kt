package com.example.userservice.client

import com.example.userservice.dto.OrderResponse
import org.springframework.cloud.openfeign.FeignClient
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable

@FeignClient(name = "order-service")
interface OrderServiceClient {

    @GetMapping("/order-service/{userId}/orders")
    fun getOrders(@PathVariable userId:String): List<OrderResponse>
}