package com.jessyt.`object`.chapter3.sub2

/**
 * Created by LYT to 2021/09/15
 */
class Max(
    private val a: Int,
    private val b: Int
)
//    : Number()
{

//    override fun toInt(): Int =
    fun toInt(): Int =
        if (a > b) a
        else b
}

class Test {
    val max = { a: Int, b: Int -> if (a > b) a else b }
}