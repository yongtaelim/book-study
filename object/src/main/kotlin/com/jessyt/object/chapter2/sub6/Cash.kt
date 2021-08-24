package com.jessyt.`object`.chapter2.sub6

/**
 * Created by LYT to 2021/08/24
 */
class Cash(
    private var dollars: Int
) {
    fun mul(factor: Int) {
        this.dollars *= factor
    }

    override fun toString(): String {
        return "$dollars"
    }
}

class ImmutableCash(
    private val dollars: Int
) {
    fun mul(factor: Int) =
        ImmutableCash(dollars * factor)
}

fun main() {
    val map = mutableMapOf<Cash, String>()
    val five = Cash(5)
    val ten = Cash(10)

    map[five] = "five"
    map[ten] = "ten"
    five.mul(2)

    println(map)
    println(map[five])
}