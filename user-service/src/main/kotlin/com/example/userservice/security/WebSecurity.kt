package com.example.userservice.security

import com.example.userservice.service.UserService
import com.fasterxml.jackson.databind.ObjectMapper
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Lazy
import org.springframework.core.env.Environment
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.web.SecurityFilterChain
import org.springframework.security.web.access.expression.WebExpressionAuthorizationManager
import org.springframework.security.web.util.matcher.AntPathRequestMatcher


@Configuration
@EnableWebSecurity
class WebSecurity(
    val objectMapper: ObjectMapper,
    @Lazy val userService: UserService,
    val environment: Environment
) {

    @Bean
    fun securityFilterChain(http: HttpSecurity): SecurityFilterChain {

        // Configure AuthenticationManagerBuilder
        val authenticationManagerBuilder = http.getSharedObject(AuthenticationManagerBuilder::class.java)
        authenticationManagerBuilder.userDetailsService(userService).passwordEncoder(passwordEncoder())
        val authenticationManager = authenticationManagerBuilder.build()
        http
            .csrf {
                it.disable()
            }
            .authorizeHttpRequests {
                it.requestMatchers(AntPathRequestMatcher("/actuator/**")).permitAll()
                    .requestMatchers(AntPathRequestMatcher("/users", "POST")).permitAll()
                    .requestMatchers(AntPathRequestMatcher("/welcome")).permitAll()
                    .requestMatchers(AntPathRequestMatcher("/health-check")).permitAll()
                    .requestMatchers(AntPathRequestMatcher("/swagger-ui/**")).permitAll()
                    .requestMatchers(AntPathRequestMatcher("/swagger-resources/**")).permitAll()
                    .requestMatchers(AntPathRequestMatcher("/v3/api-docs/**")).permitAll()
                    .requestMatchers("/**").access(
                        WebExpressionAuthorizationManager("hasIpAddress('192.168.0.2') or hasIpAddress('127.0.0.1') or hasIpAddress('172.30.96.94')")
                    )
                    .anyRequest().authenticated()
            }
            .headers {
                it.frameOptions { it.disable() }
            }
            .authenticationManager(authenticationManager)
            .addFilter(getAuthenticationFilter(authenticationManager))

        return http.build()
    }

    @Bean
    fun passwordEncoder(): BCryptPasswordEncoder = BCryptPasswordEncoder()

    private fun getAuthenticationFilter(authenticationManager: AuthenticationManager): AuthenticationFilter {
        val authenticationFilter = AuthenticationFilter(objectMapper, userService, authenticationManager, environment)
        return authenticationFilter
    }


}