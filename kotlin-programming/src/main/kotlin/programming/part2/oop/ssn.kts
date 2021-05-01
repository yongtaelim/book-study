// 인라인 클래스
/**
 * 왜 쓸까??
 * String 으로 만든 ssn 보다 SSN Inline Class를 활용하여 SSN의 고유한 값으로 만든다.
 * val ssn: String = "1-124-124-12-4" < SSN("1-124-124-12-4)
 *
 * 컴파일할 때 클래스는 사라진다.
 */
inline class SSN(val id: String)

fun receiveSSN(ssn: SSN) {
    println("Received $ssn")
}

receiveSSN(SSN("111-111-1111111"))


inline fun sum(a: Int, b: Int) = a + b