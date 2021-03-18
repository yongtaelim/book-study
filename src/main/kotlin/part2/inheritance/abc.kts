// 추상클래스
abstract class Musician(val name: String, val activeFrom: Int) {
    abstract fun instrumentType(): String
}

class Cellist(name: String, activeFrom: Int): Musician(name, activeFrom) {
    override fun instrumentType() = "String"
}

val ma = Cellist("asdf", 564)

/**
 * interface vs abstract
 * 인터페이스에 정의된 속성엔 백킹 필드가 없다.
 * 인터페이스는 구현 클래스로부터 속성을 얻는 것을 추상 메소드에 의존한다.
 * 반면에 추상 클래스는 백킹 필드를 가진다.
 *
 * 인터페이스는 한 번에 여러 개를 구현할 수 있지만, 클래스는 추상 클래스든지 일반 클래스든지 하나만 확장 가능하다.
 */