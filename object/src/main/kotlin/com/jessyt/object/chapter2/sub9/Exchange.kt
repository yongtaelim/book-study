package com.jessyt.`object`.chapter2.sub9

/**
 * Created by LYT to 2021/09/07
 */
interface BadExchange {
    fun rate(target: String): Float
    fun rate(source: String, target: String): Float
}

interface Exchange {
    fun rate(source: String, target: String): Float
    class Smart {
        private lateinit var origin: Exchange
        fun toUsd(source: String) =
            this.origin.rate(source, "USD")
    }
}