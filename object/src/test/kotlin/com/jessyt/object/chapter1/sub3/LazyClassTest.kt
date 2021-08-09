package com.jessyt.`object`.chapter1.sub3

import org.junit.jupiter.api.Test

/**
 * Created by LYT to 2021/08/09
 */
class LazyClassTest {
    @Test
    fun `lazy class test`() {
        // Given
        val test by lazy { LazyClass("111") }
        println("Create LazyClass")
        test.toInt()
        test.toInt()
        test.toInt()
    }
}