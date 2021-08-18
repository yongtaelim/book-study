package com.jessyt.`object`.chapter2.sub3

/**
 * Created by LYT to 2021/08/17
 */
interface Cash {
    fun multiply(factor: Float): Cash
}

class DefaultCash(
    private val dollars: Int
): Cash {
    override fun multiply(factor: Float): Cash {
        return DefaultCash(this.dollars * factor.toInt())
    }
}

class Employee(
    private val salary: Cash
) {

}

class Cash1 {
    fun cents(): Int {
        // logic
        return 0
    }
}

class Person {
    fun play() {
        val cash = Cash1()
        val cents = cash.cents()
        // logic
    }
}