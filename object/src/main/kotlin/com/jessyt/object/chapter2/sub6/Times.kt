package com.jessyt.`object`.chapter2.sub6

/**
 * Created by LYT to 2021/08/26
 */
class Times {

    fun test() {
        val price = Cash1()
        price.dollars = 29
        price.cents = 95
        println("price=$price")
    }

    fun test1() {
        val price = Cash(29, 95)
        println("price=$price")
    }

    fun print(price: Cash) {
        println("Now Price: $price")
        price.mul(2)
        println("After Price: $price")
    }

    fun logic() {
        val five = Cash(5)
        // 수 십줄의 로직
        print(five)
        // 수 백줄의 로직

        println(five)  // 5가 아닌 10이다.
    }
}

class Cash1(
    var dollars: Int? = null,
    var cents: Int? = null,
)