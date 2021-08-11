package com.jessyt.`object`.chapter2.sub1

/**
 * Created by LYT to 2021/08/10
 */
class Cash(
    private val digits: Int,
    private val cents: Int,
    private val currency: String
)

class Test {
    fun eq() {
        val CashA = Cash(29, 92, "USD")
        val CashB = Cash(29, 92, "USD")

        CashA == CashB
    }

}
