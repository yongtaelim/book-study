package org.kotlinlang.play.part2.oop

import java.lang.RuntimeException

// read only class
class Car1(val yearOfMake: Int)

val car1 = Car1(2019)
println(car1.yearOfMake)



// read && write class
class Car2(val yearOfMake: Int, var color: String)

val car2 = Car2(2019, "Red")
car2.color = "Green"

// 속성 제어 변경
/**
 * car3 클래스의 theColor field의 값을 바꾸기 위해 set, field를 사용
 */
class Car3(val yearOfMake: Int, theColor: String) {
    var fuelLevel = 100
    var color = theColor
    set(value) {
        if (value.isBlank()) {
            throw RuntimeException("no empty, please")
        }
        field = value
    }
}

//public final void setColor(@NotNull String value) {
//    Intrinsics.checkNotNullParameter(value, "value");
//    if (StringsKt.isBlank((CharSequence)value)) {
//        throw (Throwable)(new RuntimeException("no empty, please"));
//    } else {
//        this.color = value;
//    }
//}