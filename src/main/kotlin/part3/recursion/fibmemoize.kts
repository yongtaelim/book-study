import kotlin.system.measureTimeMillis

/*
Groovy 방식의 메모이제이션

1. memoize() 메소드를 제네릭 람다 표현식에 인젝트
2. 제네릭 람다 표현식은 T타입의 파라미터를 받고, R타입의 리턴
3. memoize()를 호출한 결과는 함수와 동일한 시그니처를 가진 함수
4. memoize() 함수에서 우리는 this를 로컬변수 original에 할당해서 오리지날 함수의 래퍼런스를 저장할 수 있다.
5. 비어있는 cache를 초기화한다.
6. T타입의 파라미터를 받고, R타입의 결과를 리턴하는 람다를 리턴한다.
7. 리턴된 함수는 결과가 존재하는지 보기 위해 캐시를 확인한다.
8. 캐시에 결과가 없다면 연산을 해서 결과를 만들고 리턴하기 전에 저장한다.
9. 이미 결과가 존재한다면 연산을 건너뛰고 저장된 값을 리턴한다.
 */

fun <T, R> ((T) -> R).memoize(): ((T) -> R) {
    val origianl = this
    val cache = mutableMapOf<T, R>()
    return { n: T -> cache.getOrPut(n) { origianl(n)} }
}

// lateinit : 코틀린은 정적 변수를 사용한다. 하지만 lateinit을 선언하여 난 잊지 않았어 ~ 이따가 선언할꺼야라고 코틀린에게 알려준다.
lateinit var fib: (Int) -> Long
fib = { n: Int ->
    when (n) {
        0, 1 -> 1L
        else -> fib(n - 1) + fib(n - 2)
    }
}.memoize()

println(measureTimeMillis { fib(40) })
println(measureTimeMillis { fib(45) })
println(measureTimeMillis { fib(500) })

/*
어렵다...... 하나도 모르겠따............


 */