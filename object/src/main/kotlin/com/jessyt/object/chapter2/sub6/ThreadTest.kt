package com.jessyt.`object`.chapter2.sub6

import kotlinx.coroutines.*

/**
 * Created by LYT to 2021/08/30
 */
fun main() {
    val price = YongCash(15, 10)
    val process = YongProcess()

    val a = GlobalScope.launch { process.go(price) }
    val b = GlobalScope.launch { process.go(price) }

    runBlocking {
        a.join()
        b.join()
    }
}

class YongProcess {
    fun go(price: YongCash) {
        println("Thread: ${Thread.currentThread().name}")
        price.mul(2)
        println(price)
    }
}

class YongCash(
    private var dollars: Int,
    private var cents: Int
) {
    fun mul(factor: Int) {
        this.dollars *= factor
        this.cents *= factor
    }

    override fun toString(): String = "$dollars.$cents"
}
