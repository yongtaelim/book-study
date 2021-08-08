package org.kotlinlang.play.part3.recursion

import java.math.BigInteger

object Factorial {
    fun factorialRec(n: Int): BigInteger =
        if (n <= 0) 1.toBigInteger() else n.toBigInteger() * factorialRec(n - 1)

    tailrec fun factorial(n: Int, result: BigInteger = 1.toBigInteger()): BigInteger =
        if (n <= 0) 1.toBigInteger() else factorial(n - 1, result * n.toBigInteger())
}

/*
이 부분은 잘 이해가 안된다.. 차근 차근 살펴보자..... show kotlin byte...으로 두개 메소드의 차이점을 알아보자

factorialRec()의 바이트코드에서 factorialRec()를 재귀적으로 호출하기 위해서 invokevirtual 명령어가 사용되었다.
그 후 BigInteger에 multiply() 메소드가 호출되었다. 즉, 재귀 프로시저가 재귀 프로세스로 컴파일 되었다는 것을 보여준다.

반면에 factorial()의 바이트코드는 invokevirtual 재귀 호출이 전혀 없다. 대신 ifgt를 호출하고 goto로 함수의 다른 부분으로 점프한다.
즉, 재귀 프로시저가 반복을 이용하는 프로세스로 컴파일 되었다는 증거다. 코틀린이 잘했단다... 무슨말인지 모르겠다..

tailrec 최적화는 재귀가 꼬리호출일 때만 동작한다. tailrec를 사용하기 위해서 우리는 factorialRec()를 재취 호출이 마지막에 나오는 factorial()로 재작성했다.
그래서 재귀가 마지막에 나오게 되었고, 꼬리재귀로 평가 되었다. 재귀가 복잡하다면 tailrec를 사용하기 쉽지 않다.

꼬리호출 최적화는 재귀를 반복으로 변환해서 스택 레벨의 숫자를 제어한다.
이런 방법은 효츌성 측면에서 영향이 있다. 하지만! 함수를 반복적으로 호출하지 않고, 저장된 값을 리턴하면 실행을 더 빠르게 할 수 있다!!
 */