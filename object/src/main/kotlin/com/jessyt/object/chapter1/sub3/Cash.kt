package com.jessyt.`object`.chapter1.sub3

/**
 * Created by LYT to 2021/08/09
 */
//class Cash(
//    private val dollars: Int
//) {
//    constructor(dollars: String): this(Integer.parseInt(dollars))
//}

class Cash(
    private val dollars: Number
) {
    constructor(dollars: String): this(StringAsInteger(dollars))

    fun cents(): Int {
        // cents로 변환 로직
        return 0
    }
}

class StringAsInteger(
    private val num: Int
): Number() {

    constructor(txt: String): this(Integer.parseInt(txt))

    override fun toByte(): Byte {
        TODO("Not yet implemented")
    }

    override fun toChar(): Char {
        TODO("Not yet implemented")
    }

    override fun toDouble(): Double {
        TODO("Not yet implemented")
    }

    override fun toFloat(): Float {
        TODO("Not yet implemented")
    }

    override fun toInt(): Int {
        return num
    }

    override fun toLong(): Long {
        TODO("Not yet implemented")
    }

    override fun toShort(): Short {
        TODO("Not yet implemented")
    }
}

class a {
    fun a() {
        val num = StringAsInteger("123")
//        if (잘못된 경우) {
//            throw Exception("문제 발생!!")
//        }

        num.toInt()



    }


}



val five = Cash("5")

val i = "5".toInt()

class CacheNumber(
    private val origin: Number
): Number() {
    private val cached = mutableListOf<Int>()
    override fun toByte(): Byte {
        TODO("Not yet implemented")
    }

    override fun toChar(): Char {
        TODO("Not yet implemented")
    }

    override fun toDouble(): Double {
        TODO("Not yet implemented")
    }

    override fun toFloat(): Float {
        TODO("Not yet implemented")
    }

    override fun toInt(): Int {
        if (this.cached.isEmpty())
            this.cached.add(origin.toInt())

        return this.cached[0]
    }

    override fun toLong(): Long {
        TODO("Not yet implemented")
    }

    override fun toShort(): Short {
        TODO("Not yet implemented")
    }
}
