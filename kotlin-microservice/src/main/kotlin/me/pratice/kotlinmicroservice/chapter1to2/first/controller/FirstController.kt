package me.pratice.kotlinmicroservice.chapter1to2.first.controller

import me.pratice.kotlinmicroservice.chapter1to2.first.service.FirstService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RestController

@RestController
class FirstController() {
    @Autowired
    lateinit var firstService: FirstService

    @GetMapping("/user/{name}")
    fun hello(@PathVariable("name") name: String) = firstService.getHello(name)

    @GetMapping("/user")
    fun helloProperties() = firstService.helloProperties()
}