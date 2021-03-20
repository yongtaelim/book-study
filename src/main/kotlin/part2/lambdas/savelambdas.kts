val names = listOf("Kim", "Park", "Hello")
val checkLength5 = { name: String -> name.length == 5 }
println(names.find(checkLength5))

val checkLength4: (String) -> Boolean = { name -> name.length == 4 }

// 타입을 두 군데서나 선언하네..? 이런짓은 하지 말자
val checkLength3: (String) -> Boolean = { name: String -> name.length == 3 }