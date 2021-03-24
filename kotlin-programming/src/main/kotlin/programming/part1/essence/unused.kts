import java.lang.RuntimeException

// kotlin에서는 클래스에 속하지 함는 함수 혹은 함수도 아닌 코드가 있는 스크립트를 실행할 수 있다.

fun nofluff() {
    println("nofluff called...")
    throw RuntimeException("oops")
}

println("not in a function, calling nofluff()")
try {
    nofluff()
} catch (e: Exception) {
    val stackTrace = e.stackTrace
    println(stackTrace[0])
    println(stackTrace[1])
}
// kotlinc -script src/main/kotlin/programming.part1.essence/unused.kts