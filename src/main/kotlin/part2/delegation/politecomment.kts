//import org.kotlinlang.play.part2.delegation.com.agileddeveloper.delegates.PoliteString
//
//
//// 델리게이트 생성자를 직접호출..
//var comment: String by PoliteString("Some nice messge")
//println(comment)
//
//comment = "This is stupid"
//println(comment)
//println("comment is of length: ${comment.length}")
//
//// 델리게이트의 생성자를 직접호출하기 보단 델리게이션 인스턴스를 리턴해주는 함수를 사용하길 원한다면 아래와 같이 할 수 있다.
//comment = String by beingpolite("Some nice message")