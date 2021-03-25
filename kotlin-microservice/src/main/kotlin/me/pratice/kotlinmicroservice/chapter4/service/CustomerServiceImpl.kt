package me.pratice.kotlinmicroservice.chapter4.service

import me.pratice.kotlinmicroservice.common.domain.Customer
import me.pratice.kotlinmicroservice.common.domain.CustomerExistException
import me.pratice.kotlinmicroservice.common.domain.Telephone
import org.springframework.stereotype.Service
import reactor.core.publisher.Mono
import reactor.kotlin.core.publisher.toFlux
import reactor.kotlin.core.publisher.toMono
import java.util.concurrent.ConcurrentHashMap

@Service
class CustomerServiceImpl: CustomerService {
    companion object {
        val initialCustomers = arrayOf(
            Customer(1, "Kotlin"),
            Customer(2, "Spring"),
            Customer(3, "Microservice", Telephone("126551651125")),
        )
    }
    val customers = ConcurrentHashMap<Int, Customer>(initialCustomers.associateBy(Customer::id))

    override fun getCustom(id: Int) = customers[id]?.toMono() ?: Mono.empty()

    override fun searchCustomers(nameFilter: String) = customers.filter {
        it.value.name.contains(nameFilter, true)
    }.map(Map.Entry<Int, Customer>::value).toFlux()

    /**
     * 아래처럼 할 경우 response 값으로 { "disposed": false, "scanAvailable": true }
     * subscribe 메소드가 Disposable 객체를 반환하고 JSON으로 직렬화되기 때문이다.
     */
//    override fun createCustomer(customerMono: Mono<Customer>) =
//        customerMono.subscribe {
//            customers[it.id] = it
//        }.toMono()

//    override fun createCustomer(customerMono: Mono<Customer>) =
//        customerMono.map {
//            customers[it.id] = it
//            it
//            // Mono.empty<Any>()
//        }.toMono()

    override fun createCustomer(customerMono: Mono<Customer>) =
        customerMono.flatMap {
            if (customers[it.id] == null) {
                customers[it.id] = it
                it.toMono()
            } else {
                Mono.error(CustomerExistException("Customer ${it.id} already exist"))
            }

        }.toMono()
}