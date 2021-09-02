package com.jessyt.`object`.chapter2.sub6

import java.lang.RuntimeException

/**
 * Created by LYT to 2021/08/24
 */
class Cash(
    private var dollars: Int,
    private var cents: Int
) {
    fun mul(factor: Int) {
        this.dollars *= factor
        if (cents < 0)
            throw RuntimeException("Failed!!!")
        this.cents *= factor
    }

    override fun toString(): String {
        return "$dollars"
    }
}

class ImmutableCash(
    private val dollars: Int,
    private val cents: Int
) {
    fun mul(factor: Int): ImmutableCash {
        if (cents < 0) // 잘못된 경우
            throw RuntimeException("Failed!!!")

        return ImmutableCash(
            dollars * factor,
            cents * factor
        )
    }
}

class Test(
    private var a: String
) {
    fun mul(factor: Int) {
        this.a = (a.toInt() * factor).toString()
    }

    override fun toString(): String {
        return a
    }
}

fun main() {
//    val map = mutableMapOf<Cash, String>()
//    val five = Cash(5, 0)
//    val ten = Cash(10, 0)

    val map = mutableMapOf<Test, String>()
    val five = Test("5")
    val ten = Test("10")

    map[five] = "five"
    map[ten] = "ten"
    five.mul(2)

    println(map)
    println(map[five])
}