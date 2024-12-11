package com.example.userservice.dto

import jakarta.validation.constraints.Email
import jakarta.validation.constraints.NotNull

data class RequestLogin(

    @NotNull(message = "Email cannot be null")
    @Email
    val email: String,

    @NotNull(message = "Password cannot be null")
    val password: String
)