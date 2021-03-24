package org.kotlinlang.play.part2.delegation.version8

// 델리게이션의 주의할 점
interface Worker8 {
    fun work()
    fun takeVacation()
    fun fileTimeSheet() = println("Why???")
}

class JavaProgrammer8 : Worker8 {
    override fun work() = println("write Java")
    override fun takeVacation() = println("code at the beach")
}

class CSharpProgrammer8 : Worker8 {
    override fun work() = println("write csharp")
    override fun takeVacation() = println("branch at the beach")
}

class Manager(var staff: Worker8) : Worker8 by staff

val manager = Manager(JavaProgrammer8())
println("Staff is ${manager.staff.javaClass.simpleName}")
manager.work()

println("change staff")

manager.staff = CSharpProgrammer8()
println("Staff is ${manager.staff.javaClass.simpleName}")
manager.work()

/**
 * Staff is JavaProgrammer
 * write Java
 * change staff
 * Staff is CSharpProgrammer
 * write Java
 */
// 이렇게 나온 이유는 Manager 클래스의 델리게이션 참조값을 변경한게 아니라 필드값만 변경했기 때문이다.