import java.lang.RuntimeException

/**
 * Kotlin은 클래스 접근자는 default로 final이다.
 * open을 붙혀줘야 상속이 가능하다. class, method, paramter 모두..
 */
open class Vehicle(val year: Int, open var color: String) {
    open val km = 0
    fun repaint(newColor: String) {
        color = newColor
    }
}

// Java와는 다르게 extends, implements로 구분하지 않고 그냥 상속!
open class Car(year: Int, color: String) : Vehicle(year, color) {
    override var km: Int = 0  // 백킹 필드
        set(value) {
            if (value < 1) {
                throw RuntimeException("can't...")
            }
            field = value
        }

    fun drive(distance: Int) {
        km += distance
    }
}

// 부모 클래스에서 private or protected 멤버를 자식 클래스에서는 public으로 만들 수 있다.
class FamilyCar(year: Int, color: String) : Car(year, color) {
    override var color: String
        get() = super.color
        set(value) {
            if (value.isEmpty()) {
                throw RuntimeException("Color required")
            }
            super.color = value
        }
}
