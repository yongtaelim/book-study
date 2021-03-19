interface Worker5 {
    fun work()
    fun takeVacation()
    fun fileTimeSheet() = println("Why...?")
}

interface Assistant {
    fun doChores()
    fun fileTimeSheet() = println("No!!")
}
//
//class DepartmentAssistant : Assistant {
//    override fun doChores() {
//        TODO("Not yet implemented")
//    }
//}
//
//class Manager(val staff: Worker, val assistant: Assistant) : Worker by staff, Assistant by assistant {
//    override fun fileTimeSheet() {
//        TODO("Not yet implemented")
//    }
//
//    override fun fileTimeSheet() {
//        TODO("Not yet implemented")
//    }
//
//}