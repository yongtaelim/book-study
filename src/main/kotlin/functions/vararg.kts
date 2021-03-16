// 적용 X
fun max(numbers: IntArray): Int {
    var large = Int.MIN_VALUE
    for (number in numbers) {
        large = if (number > large) number else large
    }
    return large
}

println(max(intArrayOf(1,5,2,5,12)))

// 적용 O
fun varargMax(vararg numbers: Int): Int {
    var large = Int.MIN_VALUE
    for (number in numbers) {
        large = if (number > large) number else large
    }
    return large
}

println(varargMax(1,5,2,5,12))

// 응용편
fun greetMany(msg: String, vararg names: String) {
    println("$msg ${names.joinToString(", ")}")
}

greetMany(msg = "Hello", "James", "John", "Tom")

// 스프레드 연산자
val values = intArrayOf(1,52,11)
println(varargMax(*values))