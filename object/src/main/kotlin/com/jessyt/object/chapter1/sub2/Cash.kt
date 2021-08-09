package com.jessyt.`object`.chapter1.sub2

/**
 * Created by LYT to 2021/08/08
 */
class Cash(
    private var dollars: Int
) {
    constructor(dollars: Float): this(dollars.toInt())
    constructor(dollars: String): this(Cash.parse(dollars))

    companion object {
        fun parse(dollars: String): Int {
            return 0
        }
    }
}

class CashBad {
    private var dollars: Int = 0
    constructor(dollars: Float) {
        this.dollars = dollars.toInt()
    }
    constructor(dollars: String) {
        this.dollars = CashBad.parse(dollars)
    }

    companion object {
        fun parse(dollars: String): Int {
            return 0
        }
    }
}

//class CashGood(
//    private var dollars: Int
//) {
//    constructor(dollars: Float) {
//        this.dollars = dollars.toInt()
//    }
//    constructor(dollars: String) {
//        this.dollars = CashBad.parse(dollars)
//    }
//
//    companion object {
//        fun parse(dollars: String): Int {
//            return 0
//        }
//    }
//}

//fun test() {
//    Cash(30)
//    Cash("$29.5")
//    Cash(29.5f)
//    Cash(29.5, "USD")
//}
