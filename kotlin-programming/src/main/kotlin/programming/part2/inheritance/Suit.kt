package org.kotlinlang.play.part2.inheritance

enum class Animal(val animalName: String) {
    ZOO("zoo"),
    CAT("cat"),
    DOG("dog");

    open fun display() = "$animalName $name"
}