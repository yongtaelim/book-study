package com.jessyt.`object`.chapter3.sub5

/**
 * Created by LYT to 2021/09/24
 */
class Cash(
    private var dollars: Int
) {
    fun getDollars() = this.dollars
    fun setDollars(dollars: Int) {
        this.dollars = dollars
    }
}

class Cash1(
    var dollars: Int
)

class Cash2(
    private val dollars: Int
) {
    fun print() {
        println("dollars:: ${this.dollars}")
    }
}

class Cash3(
    private var dollars: Int
) {

}

class Cash5(
    private val value: Int
) {
    fun dollars() = this.value
}

class Cash6(
    private val value: Int
) {
    fun getDollars() = this.value
}