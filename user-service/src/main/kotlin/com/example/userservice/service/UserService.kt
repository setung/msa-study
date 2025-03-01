package com.example.userservice.service

import com.example.userservice.client.OrderServiceClient
import com.example.userservice.dto.OrderResponse
import com.example.userservice.dto.UserRequest
import com.example.userservice.dto.UserResponse
import com.example.userservice.entity.User
import com.example.userservice.error.FeignErrorDecoder
import com.example.userservice.repository.UserRepository
import feign.FeignException
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.stereotype.Service

@Service
class UserService(
    val userRepository: UserRepository,
    val passwordEncoder: BCryptPasswordEncoder,
    val orderServiceClient: OrderServiceClient
) : UserDetailsService {

    fun createUser(userRequest: UserRequest): UserResponse {
        val user = User.from(userRequest, passwordEncoder.encode(userRequest.password))
        return UserResponse(userRepository.save(user))
    }

    fun getUserByUserId(userId: String): UserResponse {
        val user = userRepository.findByUserId(userId) ?: throw RuntimeException("user null")

        val orders = orderServiceClient.getOrders(userId)

        return UserResponse(user, orders)
    }

    fun getUserByAll() = userRepository.findAll()

    fun getUserByEmail(email: String) = userRepository.findByEmail(email) ?: throw NotFoundException()

    override fun loadUserByUsername(username: String): org.springframework.security.core.userdetails.User {
        val user: User = userRepository.findByEmail(username) ?: throw NotFoundException()

        return org.springframework.security.core.userdetails.User(
            user.email, user.password, true, true, true, true, listOf()
        )
    }
}