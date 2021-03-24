// 컴패니언 객체
/**
 * 속성이나 메소드가 클래스 레벨에서 필요하고, 클래스의 특정 인스턴스와는 관련이 없어야 한다면 클래스 안에 만들 수 없다.
 * 이 때 메소드를 컴패니언 객체로 만든다.
 * 컴패니언 객체는 클래스 안에 정의한 싱글톤이다.
 */
class MachineOperator(val name: String) {
    fun checkin() = checkedIn++
    fun checkout() = checkedIn--

    companion object {
        var checkedIn = 0
        fun minimumBreak() = "15 minutes every 2 hours"
    }
}
/**
 * companion object 키워드를 이용하여 정의
 * 컴패니언 객체 안에 있는 속성 checkedIn은 MachineOperator 클래스의 클래스 레벨 속성이 되었다.
 * 비슷한 맥락으로 minimumBreak 메소드 역시 어떤 인스턴스에도 속하지 않는 클래스 레벨 메서드이다.
 */

MachineOperator("Mater").checkin()
println(MachineOperator.minimumBreak())
println(MachineOperator.checkedIn)

// 컴패니언 접근방법
val ref = MachineOperator.Companion

// 컴패니언 명 지정
class MachineOperator1(val name: String) {
    fun checkin() = checkedIn++
    fun checkout() = checkedIn--

    companion object MachineOperatorFactory {
        var checkedIn = 0
        fun minimumBreak() = "15 minutes every 2 hours"
    }
}

val ref = MachineOperator1.MachineOperatorFactory

// 팩토리로 사용하는 컴패니언
/**
 * 컴패니언을 팩토리로 사용하기 위해서는 class에 private 생성자를 만들어야한다.
 * 컴패니언 객체에서 생성된 인스턴스를 리턴하기 전에 인스턴스를 처리하는 메소드를 하나 이상 생성한다.
 */
class MachineOperator2 private constructor(val name: String) {
    fun checkin() = checkedIn++
    fun checkout() = checkedIn--

    companion object MachineOperatorFactory {
        var checkedIn = 0
        fun create(name: String): MachineOperator2 {
            val instance = MachineOperator2(name)
            instance.checkin()
            return instance
        }
    }
}

val operator2 = MachineOperator2.create("Meter")
println(MachineOperator2.checkedIn)
