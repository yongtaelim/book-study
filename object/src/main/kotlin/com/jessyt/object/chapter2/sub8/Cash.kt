package com.jessyt.`object`.chapter2.sub8

/**
 * Created by LYT to 2021/09/04
 */
class Cash(
    private val exChange: Exchange,
    private val cents: Int
) {
    fun `in`(currency: String): Cash =
        if (currency == "USD") {
            Cash(
                this.exChange,
                (this.cents * this.exChange.rate(currency)).toInt()
            )
        } else {
            Cash(
                this.exChange,
                (this.cents * this.exChange.rate("USD", currency)).toInt()
            )
        }

    override fun toString(): String {
        return "$cents"
    }


}

interface Exchange {
    fun rate(origin: String, target: String): Double
    fun rate(target: String): Double

    class Fake: Exchange {
        override fun rate(origin: String, target: String): Double {
            return 1.15
        }

        override fun rate(target: String): Double {
            return this.rate("USD", target)
        }

    }
}