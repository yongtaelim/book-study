package me.pratice.kotlinmicroservice.chapter1to2.first.config

import me.pratice.kotlinmicroservice.chapter1to2.first.service.Example1Service
import me.pratice.kotlinmicroservice.chapter1to2.first.service.Example2Service
import me.pratice.kotlinmicroservice.chapter1to2.first.service.ExampleService
import org.springframework.boot.autoconfigure.condition.ConditionalOnExpression
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class ServiceConfiguration {
    @Bean
    @ConditionalOnExpression("#{'\${service.example.type}' == 'one'}")
    fun example1(): ExampleService = Example1Service()

    @Bean
    @ConditionalOnExpression("#{'\${service.example.type}' == 'two'}")
    fun example2(): ExampleService = Example2Service()
}