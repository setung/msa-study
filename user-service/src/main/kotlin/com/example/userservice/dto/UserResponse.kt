package com.example.userservice.dto

import com.example.userservice.entity.User

data class UserResponse(
    var id: Long,

    val name: String,

    val email: String,

    val userId: String,

    val orders: List<OrderResponse>?
) {

    constructor(user: User) : this(
        id = user.id!!,
        name = user.name,
        email = user.email,
        userId = user.userId,
        orders = null
    )

    constructor(user: User, orders: List<OrderResponse>?) : this(
        id = user.id!!,
        name = user.name,
        email = user.email,
        userId = user.userId,
        orders = orders
    )
}