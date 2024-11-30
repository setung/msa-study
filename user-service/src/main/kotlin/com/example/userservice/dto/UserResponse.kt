package com.example.userservice.dto

import com.example.userservice.entity.User

data class UserResponse(
    var id: Long,

    val name: String,

    val email: String,

    val userId: String
) {

    constructor(user: User) : this(
        id = user.id!!,
        name = user.name,
        email = user.email,
        userId = user.userId
    )
}