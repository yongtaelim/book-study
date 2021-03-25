package me.pratice.kotlinmicroservice.chapter4.service

import me.pratice.kotlinmicroservice.common.domain.Customer
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

interface CustomerService {
    fun getCustom(id: Int): Mono<Customer>
    fun searchCustomers(nameFilter: String): Flux<Customer>
    fun createCustomer(customerMono: Mono<Customer>): Mono<Customer>
}