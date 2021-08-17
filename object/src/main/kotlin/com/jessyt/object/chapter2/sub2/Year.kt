package com.jessyt.`object`.chapter2.sub2

/**
 * Created by LYT to 2021/08/16
 */
//class Year {
//    fun read() = System.currentTimeMillis() / (1000 * 60 * 60 * 24 * 30 * 12) - 1970
//}

//class Year(
//    private val millis: Millis
//) {
//    fun read() = millis.read() / (1000 * 60 * 60 * 24 * 30 * 12) - 1970
//}
//
class Millis(
    private val milliSeconds: Long
): Number() {
    fun read() = milliSeconds

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
        TODO("Not yet implemented")
    }

    override fun toLong(): Long {
        TODO("Not yet implemented")
    }

    override fun toShort(): Short {
        TODO("Not yet implemented")
    }
}

class Test {
    fun yearTest() {
        // Millis 생성
        val millis = Millis(System.currentTimeMillis())



        // Year 얻어온다.
        val year = Year(millis).read()
    }
}

class Minus(
    private vararg val num: Number
): Number() {
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
        val r = num.first().toInt()
        num.filterIndexed { index, i -> index != 0 }.forEach {r - it.toInt()}
        return r
    }

    override fun toLong(): Long {
        TODO("Not yet implemented")
    }

    override fun toShort(): Short {
        TODO("Not yet implemented")
    }
}

class Divide(
    private vararg val num: Number
): Number() {
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
        val r = num.first().toInt()
        num.filterIndexed { index, i -> index != 0 }.forEach {r / it.toInt()}
        return r
    }

    override fun toLong(): Long {
        TODO("Not yet implemented")
    }

    override fun toShort(): Short {
        TODO("Not yet implemented")
    }

}

class Multiply(
    private vararg val num: Number
): Number() {
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
        val r = num.first().toInt()
        num.filterIndexed { index, i -> index != 0 }.forEach {r * it.toInt()}
        return r
    }

    override fun toLong(): Long {
        TODO("Not yet implemented")
    }

    override fun toShort(): Short {
        TODO("Not yet implemented")
    }

}

class Year(
    private val num: Number
) {
    constructor(millis: Millis): this(
        Minus(
            Divide(
                millis,
                Multiply(1000, 60, 60, 24, 30, 12)
            ),
            1970
        )
    )

    fun read() = this.num.toInt()

}