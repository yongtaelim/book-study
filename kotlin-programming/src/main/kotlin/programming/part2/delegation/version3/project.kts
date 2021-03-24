package org.kotlinlang.play.part2.delegation.version3

interface Worker3 {
    fun work()
    fun takeVacation()
}

class JavaProgrammer3 : Worker3 {
    override fun work() = println("write Java...")
    override fun takeVacation() = println("code at the beach...")
}

class CSharpProgrammer3 : Worker3 {
    override fun work() = println("write c#...")
    override fun takeVacation() = println("branch at the beach...")
}

class Manager3 : Worker3 by JavaProgrammer3()

val doe = Manager3()
doe.work()