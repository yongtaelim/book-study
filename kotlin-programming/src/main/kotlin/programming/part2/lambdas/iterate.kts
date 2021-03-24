// 람다받기
fun walk1To(action: (Int) -> Unit, n: Int) =
    (1..n).forEach { action(it) }

walk1To({ i -> println(i) }, 5)

// 람다 파라미터를 가장 마지막
fun walt2To(n: Int, action: (Int) -> Unit) = (1..n).forEach { action(it) }

walt2To(5) { i -> println(i) }
walt2To(5) { println(it) }
walt2To(5, ::println)

fun walt3To(n: Int, action: (Int) -> Unit) = (1..n).forEach(action)
walt3To(5, ::println)