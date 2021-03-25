package me.pratice.kotlinmicroservice.chapter3.controller

import me.pratice.kotlinmicroservice.common.domain.Customer
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class Customer3Controller {

    @GetMapping(value = ["/customer"])
    fun getCustomer() = Customer(1, "Kotlin")
}