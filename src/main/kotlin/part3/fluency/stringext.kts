// 서드파티 클래스 인젝팅
fun String.isPalindrome(): Boolean {
    return reversed() == this
}

fun String.shout() = toUpperCase()

val str = "dad"
println(str.isPalindrome())  // true
println(str.shout())  // DAD

/*
이미 존재하고 있는 메소드의 동작을 바꾸지 말라!!
 */