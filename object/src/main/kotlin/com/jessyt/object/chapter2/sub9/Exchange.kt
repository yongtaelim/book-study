package com.jessyt.`object`.chapter2.sub9

/**
 * Created by LYT to 2021/09/07
 */
interface BadExchange {
    fun rate(target: String): Float
    fun rate(source: String, target: String): Float
}

interface GoodExchange {
    fun rate(source: String, target: String): Float
    class Smart {
        private lateinit var origin: GoodExchange
        fun toUsd(source: String) =
            this.origin.rate(source, "USD")

        fun eurToUsd() = this.toUsd("EUR")
    }
}

class Test {
    fun use() {
        val rate1 = GoodExchange.Smart().toUsd("EUR")

        val rate2 = GoodExchange.Smart().eurToUsd()
    }
}

interface Exchange {
    fun rate(origin: String, target: String): Float
    class Fast: Exchange {
        private lateinit var origin: Exchange
        override fun rate(source: String, target: String): Float {
            return if (source == target) 1.0f
            else origin.rate(source, target)
        }

        fun toUsd(source: String) = this.origin.rate(source, "USD")
    }
}