package me.pratice.kotlinmicroservice.chapter1to2.first.service

interface FirstService {
    fun getHello(name: String): String

    fun helloProperties(): String
}