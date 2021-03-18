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

class Manager : Worker by JavaProgrammer()

val doe = Manager()
doe.work()