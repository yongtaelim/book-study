import java.lang.RuntimeException

// java라면 아래와 같이 명령문으로 작성해아한다.
fun javaType(name: String, age: Int): String {
    var status: String
    if (age < 17) {
        status = "어려"
    } else {
        status = "많아"
    }

    return "$name, $status"
}

println(javaType("날세", 20))

// Kotlin은 if문을 표현식으로 작성한다.
fun kotlinTye(name: String, age: Int): String {
    val status = if (age < 17) "어려" else "많아"
    return "$name, $status"
}

println(kotlinTye("또 날세", 21))

// try catch 도 마찬가지다..
fun kotlinTypeException(status: Boolean): Int {
    return try {
        if (status) {
            throw RuntimeException()
        }
        1
    } catch (e: Exception) {
        2
    }
}

println(kotlinTypeException(true))
println(kotlinTypeException(false))