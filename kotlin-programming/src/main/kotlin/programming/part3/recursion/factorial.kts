import java.math.BigInteger

/*
재귀는 스택이 위험하면 스스로 StackOverflow에러를 던지고 죽는다..

왜 죽을까??
재귀는 프로시저가 되고 생성된 바이트 코드는 결국 실행이 된다!
factorialInterative() 함수는 반복을 사용하는 프로시저이다. 그리고 반복을 사용하는 프로세스로 컴파일되고 실행된다.

이와 유사하게 factorialRec()는 재귀 프로시저고 재귀 프로세스로 컴파일되고 실행될 것이다.

결론 재귀를 컴파일하면 반복이 될 수 있다. -> tralrec 어노테이션이 코틀린 컴파일러에게 지시할 내용!
 */
//tailrec fun factorialRec(n: Int): BigInteger =
//    if (n <= 0) 1.toBigInteger() else n.toBigInteger() * factorialRec(n - 1)
//
//println(factorialRec(50000000))

/*
n.toBigInteger() * factorialRec(n-1) 코드에서 factorialRec() 메소드가 마지막에 실행된다고 생각하고 싶다!!
하지만 아니다. 리턴하기 전 수행하는 연산은 곱셈이다. 이 연산은 factorialRec이 완료될 때까지 기다린다. 그래서 각 재귀 호출들의 스택 사이즈가 커지는 것이다.
다시 쉽게 정리!!
factorialRec메서드를 호출하고 호출하고 호출하고 호출하고 마지막까지 갈때까지 Stack은 쌓이게 된다. 마지막에 도착하고나서야 비로소 한개씩 실행한다.
 */

/*
해결 방법으로는 곱셈을 위해 재귀 호출이 끝날때까지 기다리는 방식이 아닌 재귀 메소드 내부에서 처리하는 방법이다.
코틀린 컴파일러가 뒤에서 조용히 최적화를 해서 반복을 이용하도록 함수를 변경했다는 사실을 알 수 있다.
 */
tailrec fun factorial(n: Int, result: BigInteger = 1.toBigInteger()): BigInteger =
    if (n <= 0) result else factorial(n - 1, result * n.toBigInteger())

println(factorial(5000))