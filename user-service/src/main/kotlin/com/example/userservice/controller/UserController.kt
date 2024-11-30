package com.example.userservice.controller

import com.example.userservice.dto.UserRequest
import com.example.userservice.dto.UserResponse
import com.example.userservice.service.UserService
import org.springframework.beans.factory.annotation.Value
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/")
class UserController(
    @Value("\${greeting.message}") val message: String,
    val userService: UserService
) {

    @GetMapping("/health_check")
    fun status() = "It's Working in User Service"

    @GetMapping("/welcome")
    fun welcome() = message

    @PostMapping("/users")
    fun createUser(@Validated @RequestBody userRequest: UserRequest): ResponseEntity<UserResponse> =
        ResponseEntity(userService.createUser(userRequest), HttpStatus.CREATED)

}