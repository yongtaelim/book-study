import java.lang.Appendable

// where를 사용한 마라미터 타입 제한
// 모든 객체가 close 메서드를 가지고 있는 것은 아니다.
//fun <T> useAndClose(input: T) {
//    input.close();
//}

// 제약조건을 사용한다.
fun <T: AutoCloseable> useAndClose1(input: T) {
    input.close()
}

val stringWriter = java.io.StringWriter()
stringWriter.append("hello")
useAndClose1(stringWriter)

// 제약조건이 2개 이상일 경우는?
// where를 사용하자
fun <T> useAndClose2(input: T) where T: AutoCloseable, T: Appendable {
    input.close()
    input.append("there")
}
