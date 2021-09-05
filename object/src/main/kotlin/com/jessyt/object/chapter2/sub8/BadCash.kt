package com.jessyt.`object`.chapter2.sub8

/**
 * Created by LYT to 2021/09/04
 */
class BadCash(
    private val exChange: BadExchange,
    private val cents: Int
) {
    fun `in`(currency: String): BadCash =
        if (currency == "USD") {
            BadCash(
                this.exChange,
                (this.cents * this.exChange.rate(currency)).toInt()
            )
        } else {
            BadCash(
                this.exChange,
                (this.cents * this.exChange.rate("USD", currency)).toInt()
            )
        }

    override fun toString(): String {
        return "$cents"
    }


}

interface BadExchange {
    fun rate(origin: String, target: String): Double
    fun rate(target: String): Double
}