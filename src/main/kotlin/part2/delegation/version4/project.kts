interface Worker {
    fun work()
    fun takeVacation()
}

class JavaProgrammer : Worker {
    override fun work() = println("write Java...")
    override fun takeVacation() = println("code at the beach...")
}

class CSharpProgrammer : Worker {
    override fun work() = println("write c#...")
    override fun takeVacation() = println("branch at the beach...")
}

// 파라미터에 델리게이터 위임하기
class Manager(val staff: Worker) : Worker by staff {
    fun meeting() = println("org meeting with ${staff.javaClass.simpleName}")
}