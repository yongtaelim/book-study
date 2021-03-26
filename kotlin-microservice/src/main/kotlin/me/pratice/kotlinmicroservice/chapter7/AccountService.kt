package me.pratice.kotlinmicroservice.chapter7

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RestController

data class Account(val id: Int, val balance: Float)
data class Customer(val id: Int, val name: String, val accounts: List<Account>)

@Service
class AccountService {
    fun getAccountsByCustomer(customerId: Int) =
        listOf(Account(1, 125F), Account(2, 500F))
}

@Service
class CustomerService {
    @Autowired
    private lateinit var accountService: AccountService

    fun getCustomer(id: Int): Customer {
        val accounts = accountService.getAccountsByCustomer(id)
        return Customer(id, "customer$id", accounts)
    }

}

@RestController
class CustomerController {
    @Autowired
    private lateinit var customerService: CustomerService

    @GetMapping("/customer/{id}")
    fun getCustomer(@PathVariable id: Int) = customerService.getCustomer(id)
}


/////////////
@Service
class AccountService1 {
    fun getAccountsByCustomer(customerId: Int) =
        listOf(Account(1, 125F), Account(2, 500F))
}

@Service
class CustomerService1(val accountService1: AccountService1) {
    fun getCustomer(id: Int): Customer {
        val accounts = accountService1.getAccountsByCustomer(id)
        return Customer(id, "customer$id", accounts)
    }

}

@RestController
class CustomerController1(val customerService1: CustomerService1) {

    @GetMapping("/customer/{id}")
    fun getCustomer(@PathVariable id: Int) = customerService1.getCustomer(id)
}


