package me.pratice.kotlinmicroservice.chapter1to2.first.controller

import me.pratice.kotlinmicroservice.chapter1to2.first.service.ExampleService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RestController

@RestController
class ExampleController() {
    @Autowired
    lateinit var exampleService: ExampleService

    @GetMapping("/example")
    fun hello(@PathVariable("name") name: String) = exampleService.getHello()
}