// 배열 만드는 방법
val alphabet = arrayOf("a", "b", "c", "d")
println("${alphabet[0]} and ${alphabet[1]}")

// smart하지 못한 방법
// 아래 코드는 Integer 클래스 타입으로 생성됨. 오버헤드가 크게 발생....
val notSmartNumbers = arrayOf(1, 2, 3, 4, 5)

// int인 primity type으로 생성하자.
val smartNumbers = intArrayOf(1, 2, 3, 4, 5)
println(smartNumbers.size)
println(smartNumbers.average())

println(Array(5) { i -> (i + 1) * (i + 1) }.sum())