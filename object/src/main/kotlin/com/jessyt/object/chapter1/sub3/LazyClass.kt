package com.jessyt.`object`.chapter1.sub3

/**
 * Created by LYT to 2021/08/09
 */
class LazyClass(private val number: Int) {
    constructor(text: String): this(Integer.parseInt(text)) {
        println("Call Constructor")
    }

    fun toInt(): Int {
        println("Call toInt!")
        return number
    }
}