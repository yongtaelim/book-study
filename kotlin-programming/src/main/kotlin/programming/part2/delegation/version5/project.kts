interface Worker5 {
    fun work()
    fun takeVacation()
    fun fileTimeSheet() = println("Why...?")
}

class JavaProgrammer5: Worker5 {
    override fun work() {
        println("java work..")
    }

    override fun takeVacation() {
        println("go vacation!!")
    }
}

interface Assistant {
    fun doChores()
    fun fileTimeSheet() = println("No!!")
}

class DepartmentAssistant : Assistant {
    override fun doChores() {
        println("routine stuff")
    }
}

class Manager(val staff: Worker5, val assistant: Assistant) : Worker5 by staff, Assistant by assistant {
    override fun fileTimeSheet() {
        assistant.fileTimeSheet()
    }
}

val manager = Manager(JavaProgrammer5(), DepartmentAssistant())

manager.work()            // java work..
manager.takeVacation()    // go vacation!!
manager.doChores()        // routine stuff
manager.fileTimeSheet()   // No!!