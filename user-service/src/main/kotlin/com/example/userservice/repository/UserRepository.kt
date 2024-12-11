package com.example.userservice.repository

import com.example.userservice.entity.User
import org.springframework.data.jpa.repository.JpaRepository

interface UserRepository : JpaRepository<User, Long> {

    fun findByUserId(userId: String): User?
    fun findByEmail(username: String): User?
}