package com.example.userservice.security

import com.example.userservice.dto.RequestLogin
import com.example.userservice.service.UserService
import com.fasterxml.jackson.databind.ObjectMapper
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import io.jsonwebtoken.security.Keys
import jakarta.servlet.FilterChain
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.bouncycastle.jcajce.BCFKSLoadStoreParameter
import org.springframework.boot.autoconfigure.security.oauth2.resource.OAuth2ResourceServerProperties.Jwt
import org.springframework.core.env.Environment
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.security.core.userdetails.User
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter
import java.util.*
import javax.crypto.SecretKey
import javax.crypto.spec.SecretKeySpec

class AuthenticationFilter(
    val objectMapper: ObjectMapper,
    val userService: UserService,
    val authManager: AuthenticationManager,
    val env: Environment
) : UsernamePasswordAuthenticationFilter() {

    override fun attemptAuthentication(request: HttpServletRequest?, response: HttpServletResponse?): Authentication {
        val (email, password) = objectMapper.readValue(request!!.inputStream, RequestLogin::class.java)
        setAuthenticationManager(authManager)
        val token = UsernamePasswordAuthenticationToken(email, password, listOf())

        return authenticationManager.authenticate(token)
    }

    override fun successfulAuthentication(
        request: HttpServletRequest?,
        response: HttpServletResponse?,
        chain: FilterChain?,
        authResult: Authentication?
    ) {
        val principal = authResult!!.principal as User
        val user = userService.getUserByEmail(principal.username)
        println(principal.username)

        val secretKey: SecretKey = SecretKeySpec(
            Base64.getDecoder().decode(env.getProperty("token.secret")),
            SignatureAlgorithm.HS256.jcaName
        )

        val token = Jwts.builder()
            .setSubject(user.userId)
            .setExpiration(Date(System.currentTimeMillis() + (env.getProperty("token.expiration_time"))!!.toLong()))
            .signWith(secretKey)
            .compact()

        println(token)

        response!!.addHeader("token", token)
        response.addHeader("userId", user.userId)
    }


}