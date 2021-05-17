/*
인라인 최적화는 없는게 기본이다.
 */
fun invokeTwo(
    n: Int,
    action1: (Int) -> Unit,
    action2: (Int) -> Unit
): (Int) -> Unit {
    println("enter invokeTwo $n")
    action1(n)
    action2(n)
    println("exit invokeTwo $n")
    return { _: Int -> println("lambda returned from invokeTwo") }
}

fun report(n: Int) {
    println("")
    print("called with $n, ")
    val stackTrace = RuntimeException().stackTrace
    println("Stack depth: ${stackTrace.size}")
    println("Partial listing of the stack:")
    stackTrace.take(3).forEach(::println)
}

fun callInvokeTwo() {
    invokeTwo2(1, { i -> report(i) }, { i -> report(i) })
}

callInvokeTwo()

/*
 인라인 함수는 함수를 호출하는 대신 함수의 바이트코드가 함수를 호출하는 위치에 들어온다.
 함수 호출의 오버헤드를 제거하지만 바이트코드가 위치한다.
 일반적으로 긴 함수를 인라인으로 사용하는 건 바람직하지 않다.
 */

inline fun invokeTwo1(
    n: Int,
    action1: (Int) -> Unit,
    action2: (Int) -> Unit
): (Int) -> Unit {
    println("enter invokeTwo $n")
    action1(n)
    action2(n)
    println("exit invokeTwo $n")
    return { _: Int -> println("lambda returned from invokeTwo") }
}

/*
 inline은 호출에 들어가는 오버헤드를 제거 할 수 있다.
 inline이 될 함수가 매우 클 경우나 함수를 여러군데에서 호출한다면 inline 쓰는 것을 고려하자.
 바이트코드가 커진다.
 */

// 선택적인 noinline
inline fun invokeTwo2 (
    n: Int,
    action1: (Int) -> Unit,
    noinline action2: (Int) -> Unit
) : (Int) -> Unit {
    println("enter invokeTwo $n")
    action1(n)
    action2(n)
    println("exit invokeTwo $n")
    return { _: Int -> println("lambda returned from invokeTwo") }
}
//
// 인라인 람다에서는 논로컬 리턴이 가능하다.
fun callInvokeTwo() {
    invokeTwo2(1,
        { i -> if (i == 1) { return } },
        { i -> if (i == 1) { return } }  // noinline이끼 때문에 return 불가
    )
}

/*
크로스인라인 파라미터
주어진 람다를 호출하는게 아니고 람다를 다른 함수로 전달하거나 콜러에게 다시 돌려준다면..?
호출하지 않은 람다는 인라인으로 만들 수 없다.
 */
inline fun invokeTwo3 (
    n: Int,
    action1: (Int) -> Unit,
    action2: (Int) -> Unit
): (Int) -> Unit {
    println("enter invokeTwo $n")
    action1(n)
    println("exit invokeTwo $n")
    return { input: Int -> action2(input)}
}

/*
 invokeTwo3가 action2를 직접 호출하지 않기 때문에 return의 람다는 inline이 될 수 없다.
 <해결방법>
   - 두 번째 파라미터를 noinline으로 마크한다. 노인라인을 하면 성능상의 이득이 없고 논로컬 리턴을 사용할 권한도 없다.
   - 두 번째 파라미터를 crossinline으로 만든다. action2 함수는 invokeTwo() 함수가 아니고 호출되는 부분에서 인라인이 된다.
 */
inline fun invokeTwo3(
    n: Int,
    action1: (Int) -> Unit,
    crossinline action2: (Int) -> Unit
): (Int) -> Unit {
    println("enter invokeTwo $n")
    action1(n)
    println("exit invokeTwo $n")
    return { input: Int -> action2(input) }
}
/*
inline은 함수를 인라인으로 만들어서 함수 호출의 오버헤드를 제거해서 함수 성능을 최적화 시킴.
crossinline도 인라인 최적화를 해준다. But! 람다가 전달된 곳이 아니라 실제로 람다가 사용된 곳에서 인라인 최적화가 진행
파라미터로 전달된 람다가 noinline이나 crossinline이 아닌 경우만 논로컬 리턴으로 쓸 수 있다.
 */

/*
return과 inline과 연관된 좋은 예시
- 라벨이 없는 리턴은 항상 함수에서 발생하며 람다에서는 발생하지 않는다.
- 라벨이 없는 리턴은 인라인이 아닌 람다에서 허용되지 않는다.
- 함수명은 라벨의 기본 값이 되지만 이를 맹신하지 말자. 라벨 리턴을 사용할 거라면 항상 라벨명을 지어야한다.
- 일반적으로 코드 최적화를 하기 전에 성능 측정을 먼저 하자. 람다를 사용하는 코드라면 성능 측정을 먼저하자
- inline은 눈에 띄는 성능 향상이 있을 때만 사용
 */
