package com.jessyt.`object`

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class MongoApplication

fun main(args: Array<String>) {
    runApplication<MongoApplication>(*args)
}
