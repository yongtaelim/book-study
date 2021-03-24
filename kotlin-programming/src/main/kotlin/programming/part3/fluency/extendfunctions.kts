/*
 함수를 확장

 andThen() 메소드를 인젝트 해보자.
 andThen 메소드는 T -> R -> U
 */

fun <T, R, U> ((T) -> R).andThen(next: (R) -> U): (T) -> U =
    { input: T -> next(this(input)) }

// practice.. 단독함수 생성
fun increment(number: Int): Double = number + 1.toDouble()
fun double(number: Double) = number * 2

val incrementAndDouble = ::increment.andThen(::double)
println(incrementAndDouble(5)) // 12.0

// 이런식으로 되는 구나........................ 놀랍다...........
