package org.kotlinlang.play.part2.oop.com.agiledeveloper.util

import java.lang.RuntimeException

fun unitsSupported() = listOf("Metric", "Imperal")
fun precision(): Int = throw RuntimeException("Not implemented yet")

object Templerature {
    fun c2f(c: Double) = c * 9.0/5 + 32
    fun f2c(f: Double) = (f - 32) * 5.0/9
}

object Distance {
    fun milesToKm(miles: Double) = miles * 1.609344
    fun kmToMiles(km: Double) = km / 1.609344
}