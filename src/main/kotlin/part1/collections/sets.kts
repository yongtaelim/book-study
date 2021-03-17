// 리스트와 비슷한 맥락이다.

val fruits: Set<String> = setOf("Apple", "Banana", "Apple")
println(fruits)

// kind
// MutableSetOf() : 선호하지 않음.
// hashSetOf(), linkedSetOf(), sortedSetOf()