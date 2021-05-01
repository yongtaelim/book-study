package org.kotlinlang.play.part2.delegation.version4

interface Worker4 {
    fun work()
    fun takeVacation()
}

class JavaProgrammer4 : Worker4 {
    override fun work() = println("write Java...")
    override fun takeVacation() = println("code at the beach...")
}

class CSharpProgrammer4 : Worker4 {
    override fun work() = println("write c#...")
    override fun takeVacation() = println("branch at the beach...")
}

// 파라미터에 델리게이터 위임하기
class Manager4(val staff: Worker4) : Worker4 by staff {
    fun meeting() = println("org meeting with ${staff.javaClass.simpleName}")
}

val doe = Manager4(JavaProgrammer4())
val reo = Manager4(CSharpProgrammer4())



class Manager4(val staff: Worker4) : Worker4 by staff {
    override fun takeVacation() {
        println("of course")
    }
}