package com.example.userservice.entity

import com.example.userservice.dto.UserRequest
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import java.util.*

@Entity
class User(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null,

    val name: String,

    val email: String,

    val password: String,

    val userId: String
) {
    companion object {
        fun from(userRequest: UserRequest, encodedPassword: String) = User(
            name = userRequest.name,
            email = userRequest.email,
            password = encodedPassword,
            userId = UUID.randomUUID().toString()
        )
    }

}