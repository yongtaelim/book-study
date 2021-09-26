package com.jessyt.`object`.chapter3.sub6

/**
 * Created by LYT to 2021/09/24
 */
class BadCash(
    private val dollars: Int
) {
    fun euro() =
        Exchange().rate("USD", "EUR") * this.dollars
}

open class Exchange {
    fun rate(a: String, b: String) = 0
}

class FakeExchange(): Exchange() {

}

fun badTest() {
    val five = BadCash(5)
    println("$5 equals to ${five.euro()}")
}

class Cash(
    private val dollars: Int,
    private val exchange: Exchange
) {
    constructor(): this(0)
    constructor(dollars: Int): this(dollars, Exchange())

    fun euro() =
        this.exchange.rate("USD", "EUR") * this.dollars
}

fun test() {
    val five = Cash(5, FakeExchange())
    println("$5 equals to ${five.euro()}")
}