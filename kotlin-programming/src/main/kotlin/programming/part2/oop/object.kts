// 익명 객체 선언방법
// 익명 객체의 내부 타입은 함수나 메소드의 리턴타입, 파라미터가 될 수 없다.
fun drawCircle() {
    val circle = object {
        val x = 10
        val y = 20
        val radius = 30
    }
    println("Circle x : ${circle.x} y : ${circle.y} radius : ${circle.radius}")
}

drawCircle()