// 객체선언을 이용한 싱글톤
/**
 * 객체 선언을 이용해서 만들 Util 객체가 싱글톤이다.
 * Util로는 객체 생성 불가
 * 코틀린 컴파일러는 Util을 클래스로 취급 X
 * Util은 이미 객체상태
 * Java의 private 생성자와 static 메소드만 가지고 있는 클래스와 동일
 * @JvmStatic 어노테이션을 사용하지 않았다면 해당 메소드는 바이트코드에서 static이 되지 않는다.
 */
object Util {
    fun numberOfProcessors() = Runtime.getRuntime().availableProcessors()
}

// 아래와 동일
//public static final class Util {
//    public final int numberOfProcessors() {
//        return Runtime.getRuntime().availableProcessors()
//    }
//}

// 호출 방법
println(Util.numberOfProcessors())