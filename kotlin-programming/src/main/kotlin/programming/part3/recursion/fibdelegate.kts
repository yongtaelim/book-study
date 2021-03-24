import kotlin.reflect.KProperty
import kotlin.system.measureTimeMillis

/*
델리게이트를 이용한 메모이제이션

델리게이트는 내부적으로 캐시를 가지고 있고, 오리지날 함수는 func 프로퍼티를 가지고 있다.
getValue() 함수가 값이 캐시에 없을 경우 오리지날 함수를 실행시키는 람다표현식을 리턴한다.
 */
class Memoize<T, R>(val func: (T) -> R) {
    val cache = mutableMapOf<T, R>()
    operator fun getValue(thisRef: Any?, property: KProperty<*>) = { n: T ->
        cache.getOrPut(n) { func(n) }
    }
}

val fib: (Int) -> Long by Memoize { n: Int ->
    when (n) {
        0, 1 -> 1L
        else -> fib(n - 1) + fib(n - 2)
    }
}

println(measureTimeMillis { fib(40) })
println(measureTimeMillis { fib(50) })
println(measureTimeMillis { fib(500) })