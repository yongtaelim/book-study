import kotlin.io.println as println1

fun printNumbers() {
    val range = 1..10
    for (i in range) {
        println1(i)
    }
}

fun printNumbers() {
    val range = 1..10
    range.forEach { i -> println1(i) }
}

fun printNumbers() {
    val range = 1..10
    range.forEach { println1(it) }
}

fun printNumbers() {
    (1..10).forEach { println1(it) }
}

fun printNumbers() {
    (1..10).forEach(::println1)
}