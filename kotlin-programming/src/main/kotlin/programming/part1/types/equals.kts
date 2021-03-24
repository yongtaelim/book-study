// java code
// 먼저 타입을 비교하고 캐스팅을 한 후 비교가 가능하다.
//@Override
//public boolean equals(Object other) {
//    if (other instanceof Animal) {
//        return age == ((Animal) other).age;
//    }
//
//    return false;
//}

// Kotlin Code
// Smart Cast
// 캐스팅 할 필요 없다. 조건문에서 이미 확실한 판정을 받았기 때문이다.

class Animal {
    var age: Int = 0

//    override fun equals(other: Any?): Boolean {
//        return if (other is Animal) age == other.age else false;
//    }

    // refectoring
    override fun equals(other: Any?) = other is Animal && age == other.age
}



