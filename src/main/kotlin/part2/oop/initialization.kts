import java.lang.RuntimeException

/**
 * init 블록으로 초기화 할 수 있다.
 * 되도록이면 만들지 말자. 만들어도 1개까지만 만들자.
 */
class Car(val yearOfMake: Int, theColor: String) {
    var fuelLevel = 100
        private set
    var color = theColor
        set(value) {
            if (value.isBlank()) {
                throw RuntimeException("no empty, please")
            }
            field = value
        }

    init {
        if (yearOfMake < 2020) {
            fuelLevel = 90
        }
    }
}