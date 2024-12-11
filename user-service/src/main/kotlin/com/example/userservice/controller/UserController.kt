package com.example.userservice.controller

import com.example.userservice.dto.UserRequest
import com.example.userservice.dto.UserResponse
import com.example.userservice.service.UserService
import org.springframework.beans.factory.annotation.Value
import org.springframework.core.env.Environment
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping
class UserController(
    @Value("\${greeting.message}") val message: String,
    val environment: Environment,
    val userService: UserService
) {

    @GetMapping("/health_check")
    fun status() =
        String.format("It's Working in User Service on PORT %s", environment.getProperty("local.server.port"))

    @GetMapping("/welcome")
    fun welcome() = message

    @PostMapping("/users")
    fun createUser(@Validated @RequestBody userRequest: UserRequest): ResponseEntity<UserResponse> =
        ResponseEntity(userService.createUser(userRequest), HttpStatus.CREATED)

    @GetMapping("/users")
    fun getUsers() = ResponseEntity(userService.getUserByAll(), HttpStatus.OK)

    @GetMapping("/users/{userId}")
    fun getUser(@PathVariable userId: String) = ResponseEntity(userService.getUserByUserId(userId), HttpStatus.OK)
}