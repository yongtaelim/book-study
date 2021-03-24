import kotlin.system.measureTimeMillis

/*
연산시간이 오래 걸리는 메소드..
 */
fun fib(n: Int): Long = when (n) {
    0, 1 -> 1L
    else -> fib(n - 1) + fib(n - 2)
}

println(measureTimeMillis { fib(40) })
println(measureTimeMillis { fib(45) })