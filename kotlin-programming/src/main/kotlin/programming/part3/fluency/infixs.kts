/*
점과 괄호...
obj instanceof String  -> good code 가독성이 뛰어나다
kotlin에서도 가능하다. 이런 기법을 중위표기법이라고 한다.
 */
data class Point(val x: Int, val y: Int)
data class Circle(val cx: Int, val cy: Int, val radius: Int)

//operator fun Circle.contains(point: Point) =
//    (point.x - cx) * (point.y - cx) + (point.y - cy) * (point.y - cy) < radius * radius

//println(circle.contains(point1))
//println(point1 in circle)

operator infix fun Circle.contains(point: Point) =
    (point.x - cx) * (point.y - cx) + (point.y - cy) * (point.y - cy) < radius * radius

val circle = Circle(100, 100, 25)
val point1 = Point(110, 110)

println(circle.contains(point1)) // true
println(circle contains point1) // true

/*
한계...
infix 메소드는 정확히 하나의 파라미터만 받아야한다!
vararg도 사용할 수 없고 기본 파라미터도 사용 불가능하다!
 */