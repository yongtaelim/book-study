// 기본 인자
//fun greet(name: String): String = "Hello $name"

// 기본 인자 + 명시적 인자
fun greet(name: String, msg: String = "Hello"): String = "$msg $name"

println(greet("yong"))


