package me.pratice.kotlinmicroservice.chapter7.classes1.application

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RestController

@Configuration
class ContextConfiguration {
    @Bean
    fun storeService() = StoreService()

    @Bean
    fun staffService(storeService: StoreService) = StaffService(storeService)

    @Bean
    fun staffController(staffService: StaffService) = StaffController(staffService)
}

data class Store(val id: Int, val balance: Float)
data class Staff(val id: Int, val name: String, val stores: List<Store>)

class StoreService {
    fun getStoresByStaff(customerId: Int) =
        listOf(Store(1, 125F), Store(2, 500F))
}


class StaffService(val storeService: StoreService) {
    fun getStore(id: Int): Staff {
        val stores = storeService.getStoresByStaff(id)
        return Staff(id, "staff$id", stores)
    }

}

@RestController
class StaffController(val staffService: StaffService) {

    @GetMapping("/staff/{id}")
    fun getCustomer(@PathVariable id: Int) = staffService.getStore(id)
}