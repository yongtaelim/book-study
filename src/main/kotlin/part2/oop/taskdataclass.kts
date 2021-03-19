// 데이터 클래스
data class Task(val id: Int, val name: String, val completed: Boolean, val assigned: Boolean)

val task = Task(1, "Create org.kotlinlang.play.part2.delegation.version3.org.kotlinlang.play.part2.delegation.version4.org.kotlinlang.play.part2.delegation.version8.org.kotlinlang.play.part2.delegation.version2.Project", false, assigned = true)
println(task)

// 구조분해를 하지 않는 경우
println(task.assigned)
println(task.id)
println(task.name)
println(task.completed)

// 구조분해를 하는 경우
val (id, _, _, isAssigned) = task