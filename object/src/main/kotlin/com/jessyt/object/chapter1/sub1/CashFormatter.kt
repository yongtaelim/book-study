package com.jessyt.`object`.chapter1

/**
 * Created by LYT to 2021/08/08
 */
class CashFormatter(
    val dollars: Int
) {
    fun format(): String {
        return String.format("$.d", this.dollars)
    }
}

class Cash(
    val dollars: Int
) {
    fun usd(): String {
        return String.format("$.d", this.dollars)
    }
}

/**
 * 소수 값만 리턴하는 클래스
 * @property origin IntArray
 * @constructor
 */
class PrimeNumbers(val origin: IntArray) {
    /**
     * 소수 값 filter
     */
    fun filterPrime() {
        origin.filter { isPrime(it) }
    }

    /**
     * 소수 체크 여부
     * @param x Int
     * @return Boolean
     */
    private fun isPrime(x: Int): Boolean {
        // 소수 체크
        return true
    }
}

/**
 * 소수 숫자 조회
 * @param origin IntArray
 * @param primes IntArray
 * @param size Int
 */
fun findPrimeNumbers(origin: IntArray, primes: IntArray, size: Int) {
    var i = 0
    while (i < size) {
        primes[0] = isPrime(origin[i])
            i++
    }
}

/**
 * 소수 체크 여부
 * @param x Int
 * @return Int
 */
fun isPrime(x: Int): Int {
    return 0
}
