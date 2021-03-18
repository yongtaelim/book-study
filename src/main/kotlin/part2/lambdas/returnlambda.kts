// 람다를 중복사용..
val names = listOf("Pam", "Pat", "Namas", "Soms")
println(names.find { name -> name.length == 4})
println(names.find { name -> name.length == 5})

// 람다를 리턴하는 함수
fun predicationOfLength(length: Int): (String) -> Boolean {
    return { input: String -> input.length == length}
}

println(names.find(predicationOfLength(4)))
println(names.find(predicationOfLength(5)))

// refectoring
fun predicationOfLength1(length: Int) =
    { input: String -> input.length == length}