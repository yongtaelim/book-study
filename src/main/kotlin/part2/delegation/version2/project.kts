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

class Manager

class Manager(val worker: Worker) {
    fun work() = worker.work()
    fun takeVacation() = worker.takeVacation()
}

// 이렇게 사용하면 좋치 않다.
// 인터페이스에 새로운 메서드를 추가할 때마다 다 추척하여 한개씩 추가해줘야한다.
// OCP 원칙에 위배
//val manager = Manager(JavaProgrammer())

