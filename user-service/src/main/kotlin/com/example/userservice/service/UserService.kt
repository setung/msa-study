package com.example.userservice.service

import com.example.userservice.dto.UserRequest
import com.example.userservice.dto.UserResponse
import com.example.userservice.entity.User
import com.example.userservice.repository.UserRepository
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.stereotype.Service

@Service
class UserService(
    val userRepository: UserRepository,
    val passwordEncoder: BCryptPasswordEncoder
) {

    fun createUser(userRequest: UserRequest): UserResponse {
        val user = User.from(userRequest, passwordEncoder.encode(userRequest.password))
        return UserResponse(userRepository.save(user))
    }
}