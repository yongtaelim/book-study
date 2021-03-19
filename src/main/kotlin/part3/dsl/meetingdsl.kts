/*
목표!!!!!!!!!!!!!!!!!!!!!!
"Release Planning" meeting {
    start at 14.30
    end by 15.20
}
 */

/*
meeting {} ................................................

meeting 메소드를 String 클래스에 확장 함수로 인젝트한다!
meeting()을 infix 메소드로 만들어서 점을 제거한다!
 */

/*
infix fun String.meeting(block: () -> Unit) {
    println("step 1 accomplished")
}

"Release Planning" meeting {}
 */


/*
Meeting 클래스를 사용하여 외의의 세부사항인 상태를 가지는 클래스를 생성해보자..............................
 */

/*
class Meeting
infix fun String.meeting(block: Meeting.() -> Unit) {
    val meeting = Meeting()
    meeting.block()  // block 실행
    println(meeting)
}
 */

/*
"Release Planning" meeting {
    println("With in lambda: $this")
}
 */

/*
With in lambda: Meetingdsl$Meeting@6b25ef1c
Meetingdsl$Meeting@6b25ef1c
 */

/*
at, by 메소드를 만들고 람다 안에서 둘 다 실행시켜 보는 것!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
 */

//class Meeting(val title: String) {
//    var startTime: String = ""
//    var endTime: String = ""
//
//    private fun convertToString(time: Double) = String.format("%.02f", time)
//    fun at(time: Double) { startTime = convertToString(time) }
//    fun by(time: Double) { endTime = convertToString(time) }
//    override fun toString() = "$title Meeting starts $startTime ends $endTime"
//}
//
//infix fun String.meeting(block: Meeting.() -> Unit) {
//    val meeting = Meeting(this)
//    meeting.block()
//    println(meeting)
//}
//
//"Release Planning" meeting {
//    at(15.20)
//    by(16.30)
//}

class Meeting(val title: String) {
    var startTime: String = ""
    var endTime: String = ""
    var start = this
    var end = this

    private fun convertToString(time: Double) = String.format("%.02f", time)
    infix fun at(time: Double) { startTime = convertToString(time) }
    infix fun by(time: Double) { endTime = convertToString(time) }
    override fun toString() = "$title Meeting starts $startTime ends $endTime"
}

infix fun String.meeting(block: Meeting.() -> Unit) {
    val meeting = Meeting(this)
    meeting.block()
    println(meeting)
}

"Release Planning" meeting {
    start at 15.20
    end by 16.30
}