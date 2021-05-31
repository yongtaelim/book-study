data class Point(val x: Int, val y: Int)
data class Circle(val cx: Int, val cy: Int, val radius: Int)

// 확장 함수를 이용하여 메소드 인젝팅!
fun Circle.contains(point: Point) =
    (point.x - cx) * (point.y - cx) + (point.y - cy) * (point.y - cy) < radius * radius
//
val circle = Circle(100, 100, 25)
val point1 = Point(110, 110)
val point2 = Point(10, 100)
println(circle.contains(point1))  // true
println(circle.contains(point1))  // false

/*
이런것도 되네!?
다른점은 class안에 메소드를 생성하면 Circle.가 빠지는 차이다.
 */

/*
사용 범위
같은 파일이거나 메소드가 있는 패키지를 임포트한 경우는 사용할 수 있다
 */

/*
확장 함수는 패키지의 static 메소드로 생성된다.
메소드 호출로 보이는 과정은 사실은 static 메소드를 호출하는 과정과 동일하다!
 */

/*
확장함수의 한계
확장함수와 인스턴스 메소드가 같은 이름을 가지고 있어서 충돌을 일으키면 항상 인스턴스 메소드가 실행된다.
인스턴스의 캡슐화된 부분에 접근할 수 있는 인스턴스 메소드와는 다르게 확장 함수는 정의된 패키지 안에서 객체에 보이는 부분에만 접근가능하다.
*/

// 확장 함수를 이용하여 연산자 인젝팅
operator fun Circle.contains(point: Point) =
    (point.x - cx) * (point.y - cx) + (point.y - cy) * (point.y - cy) < radius * radius

println(circle.contains(point1))  // true
println(point1 in circle)  // true
println(point2 in circle)  // false

// 확장 속성을 이용한 속성 인젝팅
val Circle.area: Double
    get() = kotlin.math.PI * radius * radius

val circle2 = Circle(100, 100, 25)
println("Area is ${circle2.area}")


