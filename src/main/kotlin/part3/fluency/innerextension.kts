class Point(x: Int, y: Int) {
    private val pair = Pair(x, y)
    private val firstsign = if (pair.first < 0) "" else "+"
    private val seconsign = if (pair.second < 0) "" else "+"
    override fun toString() = pair.point2String()
    fun Pair<Int, Int>.point2String() =
        "(${firstsign}${first}, ${this@Point.seconsign}${this.second})"
}

println(Point(1, -3))
println(Point(-3, 4))

/*
(+1, -3)
(-3, +4)
 */

/*
확장 함수가 클래스 내부에 생성되어서 this와 this@Point 두개의 리시버를 가지고 있다.
this : 익스텐션 리시버, 확장 함수가 실행되는 객체 (Point)
this@Point : 디스패치 리시버, 확장 함수를 만들어 추가한 클래스의 인스턴스 (Pair)
우선 순위 : 익스텐션 리시버 > 디스패치 리시버
 */