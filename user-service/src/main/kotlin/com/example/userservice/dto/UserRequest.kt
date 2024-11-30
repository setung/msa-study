package com.example.userservice.dto

import jakarta.validation.constraints.Email
import jakarta.validation.constraints.NotNull
import jakarta.validation.constraints.Size


data class UserRequest(
    @field:NotNull(message = "Email cannot be null")
    @field:Size(min = 2, message = "Email not be less than two characters")
    @field:Email
    val email: String,

    @field:NotNull(message = "name cannot be null")
    @field:Size(min = 2, message = "name not be less than two characters")
    val name: String,

    @field:NotNull(message = "password cannot be null")
    @field:Size(min = 8, message = "password must be equal or greater than 8 characters")
    val password: String,
) {
}
