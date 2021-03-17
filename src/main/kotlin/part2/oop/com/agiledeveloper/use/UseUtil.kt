package org.kotlinlang.play.part2.oop.com.agiledeveloper.use

import org.kotlinlang.play.part2.oop.com.agiledeveloper.util.Templerature
import org.kotlinlang.play.part2.oop.com.agiledeveloper.util.unitsSupported

fun main() {
    println(unitsSupported())
    println(Templerature.f2c(75.253))
    println(Templerature.c2f(22.154))
}

/**
 * 패키지, 함수, 싱글톤을 어떻게 사용하는지 보여주는 것과 더불어 이 예제는 우리가 왜 용도에 따라 탑레벨 함수나 싱글톤을 선택해야하는지를 보여준다.
 * 사용할 함수들이 하이레벨이거나 일번적이거나 넓게 사용될 예정이라면 패키지 안에 직접 넣어서 탑레벨 함수로 사용하는게 좋다.
 * 반대로 함수들이 연관되어 있다면 (c2f, f2c) 싱글톤을 사용하는게 좋다.
 */
