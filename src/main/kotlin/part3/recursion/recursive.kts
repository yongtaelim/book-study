import java.math.BigInteger

fun factorialRec(n: Int): BigInteger =
    if (n <= 0) 1.toBigInteger() else n.toBigInteger() * factorialRec(n -1)

println(factorialRec(5))

// 위 재귀함수와 동일한 기능.. 이것보단 재귀함수가 더 인식하기 편하다!
//fun factorialIterative(n: Int) =
//    (1..n).fold(BigInteger("1")) { product, e -> product * e.toBigInteger()}

// StackOverflowError 발생...
/*
재귀의 위험성..

재귀는 스택이 위험할 정도로 큰 레벨에 도달하면 프로그램이 뻗는다.
 */
println(factorialRec(5000000))