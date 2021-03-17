import java.lang.RuntimeException

// Method 접근자 설정
class Person(val first: String, val last: String) {
    internal fun fullName() = "$last $first"
    private fun yearsOfService(): Int = throw RuntimeException("Not implemented yet")
}

val jane = Person("Jane", "Doe")
println(jane.fullName())
//jane.ye...