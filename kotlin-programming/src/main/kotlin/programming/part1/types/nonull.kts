import java.lang.RuntimeException

fun computeSqrt(n: Double): Double {
    if (n >= 0) {
        return Math.sqrt(n)
    } else {
        throw RuntimeException()  // Nothing 타입
    }
}

// NPE.. 정말 짜증나는 Exception.. Java에서는 너무 많이 발생
// Optional로 막아보려 했으나 개발자가 작성해야하고 컴파일 과정에도 null을 리턴해도 컴파일 된다.
// 코틀린은 null 자체를 잡아준다.
//fun nickName(name: String):String {
//    if (name == "Tom") {
//        return "Coffee"
//    }
//    return null   // 컴파일 에러 발생..
//}

//println(nickName(null)) // 여기도 컴파일 에러 발생..
// Kotlin은 String일 경우 null 자체를 사용할 수 없게 했다.

// Nullable로 하려면 타입 뒤에 '?'를 붙힌다. 근데.. 안하는게 나을지도..

// 또 좋은기능!! 개발자가 놓칠 수 있는 NPE를 잡아준다.
//fun nickName(name: String?): String? {
//    if (name == "Tom") {
//        return "Coffee"
//    }
//    return name.reversed();  // Error!!
//}

// 아래처럼 하면 Error가 발생하지 않는다.. 하지만.. 마음에 안듦
fun nickName1(name: String?): String? {
    if (name == "Tom") {
        return "Coffee"
    }

    if (name != null) {
        return name.reversed()
    }
    return null
}

// 개선 1. safe-call 연산자
fun nickName2(name: String?): String? {
    if (name == "Tom") {
        return "Coffee"
    }

    return name?.reversed()  // safe-call 연산자
}

// 개선 2. 엘비스 연산자
fun nickName3(name: String?): String? {
    if (name == "Tom") {
        return "Coffee"
    }

//    val result = name?.reversed()?.toUpperCase()
//    return if (result == null) "Joker" else result

    return name?.reversed()?.toUpperCase() ?: "Joker"
}

// !! 이런 연산자가 있다. NPE 나길 바라는 마음이라면 사용하자. 쓰지말자 삭제..

// 개선 3. when
fun nickName4(name: String?) = when (name) {
    "Tom" -> "Coffee"
    null -> "Joker"
    else -> name.reversed().toUpperCase()
}