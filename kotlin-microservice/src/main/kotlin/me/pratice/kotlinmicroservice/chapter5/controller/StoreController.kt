package me.pratice.kotlinmicroservice.chapter5.controller

import me.pratice.kotlinmicroservice.chapter5.service.StoreService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RestController

@RestController
class StoreController(private val storeService: StoreService) {
    @GetMapping(value = ["/stores/{id}"])
    fun getStore(@PathVariable("id") id: Int) = storeService.getStore(id)

    @GetMapping(value = ["/stores"])
    fun getAllStore() = storeService.getAllStores()
}