import java.math.BigInteger

class Counter(val value: Int) {
    operator fun inc() = Counter(value + 1)
    operator fun dec() = Counter(value - 1)
    override fun toString() = "$value"
}

/*
절제하자
코드를 읽는 사람 입장에서 당연하게 받아들여질 경우만 사용하자
오버로딩 연산자는 일반적인 연산자의 동작이어야한다
변수이름을 의미있게 만들어라. 그래야 오버로딩의 문맥을 파악하기 좋다.
 */

val one = BigInteger("1")
val two = BigInteger("2")

val result = one + two