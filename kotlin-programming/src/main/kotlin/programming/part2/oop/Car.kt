package org.kotlinlang.play.programming.part2.oop

/**
 * Created by LYT to 2021/04/28
 */
class Car(val yearOfMake: Int, var color: String)

fun useCarObject(): Pair<Int, String> {
    val car = Car(2019, "Red")
    val year = car.yearOfMake
    car.color = "Green"
    val color = car.color
    return year to color
}

