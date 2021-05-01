package org.kotlinlang.play.programming.part2.oop

/**
 * Created by LYT to 2021/04/28
 */
class Car1(val name: String) {
    var fullName = ""
    private set

    init {
        fullName = "ㄴㅐ부  $name"
    }
}

