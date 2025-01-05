package com.example.userservice.error

import feign.Response
import feign.codec.ErrorDecoder
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Component
import org.springframework.web.server.ResponseStatusException
import kotlin.Exception

@Component
class FeignErrorDecoder: ErrorDecoder {

    override fun decode(p0: String?, p1: Response?): java.lang.Exception? {
        when(p1!!.status()) {
            404 -> {
                if(p0!!.contains("getOrders")) {
                    return ResponseStatusException(HttpStatus.valueOf(p1.status()), "User's orders is empty")
                }
            }
            else -> Exception(p1.reason())
        }

        return null
    }
}