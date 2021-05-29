operator fun Pair<Int, Int>.plus(other: Pair<Int, Int>) =
    Pair(first + other.first, second + other.second)

val firstPair = Pair(1, 2)
val secondPair = Pair(3, 4)

val response = firstPair + secondPair
println("first: ${response.first} second: ${response.second}")
// first: 4 second: 6