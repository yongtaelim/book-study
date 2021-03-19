package org.kotlinlang.play.part2.internal

// 외부 반복자
val numbers = listOf(10, 12, 15, 17, 18, 19)
for (number in numbers) {
    if (number % 2 == 0) {
        print("$number, ")
    }
}

val doubled = mutableListOf<Int>()
for (number in numbers) {
    if (number % 2 == 0) {
        doubled.add(number)
    }
}
println(doubled)

// 내부 반복자
numbers.filter { e -> e % 2 == 0 }
    .forEach { e -> print("$e, ") }
// filter, forEach 는 고차함수이다.

numbers.filter { e -> e % 2 == 0 }
    .map { e -> e * 2 }
println(doubled)
// 내부 반복자는 함수형 파이프라인이다.



