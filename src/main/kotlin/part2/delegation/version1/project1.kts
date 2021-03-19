interface Worker1 {
    fun work()
    fun takeVacation()
}

class JavaProgrammer : Worker1 {
    override fun work() = println("write Java...")
    override fun takeVacation() = println("code at the beach...")
}

class CSharpProgrammer : Worker1 {
    override fun work() = println("write c#...")
    override fun takeVacation() = println("branch at the beach...")
}

class Manager

/**
 * 가정
 * Manager가 JavaProgrammer에게 일을 시킨다고 가정해보자!
 * 상속만 사용할 경우 Manager에서 JavaProgrammer를 상속받아 사용한다.
 * 그리도 Manager가 CSharpProgrammer에게 일을 시킨다고 했을때.. 어떻게해...?
 * 상속의 단점이다.
 */