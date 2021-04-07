// 제네릭 클래스
// 제네릭에 조건이 하나만 있으므로 where절을 사용하지 않고 :만 사용하자!
class PriorityPair<T : Comparable<T>>(member1: T, member2: T) {
    val first: T
    val second: T

    init {
        if (member1 >= member2) {
            first = member1
            second = member2
        } else {
            first = member2
            second = member1
        }
    }

    override fun toString(): String {
        return "$first $second"
    }
}

println(Proritypair(2, 1))  // 2, 1
println(Proritypair("A", "B"))