package me.pratice.kotlinmicroservice.chapter1to2.first.service

import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service

@Service
class FirstServiceImpl: FirstService {
    @Value(value = "\${service.message.text}")
    private lateinit var text: String

    override fun getHello(name: String) = "hello $name"

    override fun helloProperties() = "hello $text"
}