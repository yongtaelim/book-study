// 리스트를 만드는 방법은 이뮤터블과 뮤터블이 있다.

// 이뮤터블 리스트를 만들려면 : listOf() .. ( Kotlin에서는 이뮤터블 선호 )
// 뮤터블 리스트 : mutableListOf()
val fruits: List<String> = listOf("Apple", "Banana", "Grape")
println(fruits)

// [0], [1].. 인덱스 연산자를 사용하면 내부적으로 get() 사용
println("first fruit : ${fruits[0]}, second fruit : ${fruits[1]}")

// java와 동일하게 contains 메서드 존재
println(fruits.contains("Apple"))
println("Apple" in fruits)

//이뮤터블 리스트이기 때문에 add 불가..
//fruits.add("Oranage")

// list에 추가하는 방법
// 이 방법은 기존 리스트를 카피하고 새로운 리스트에 요소를 추가한다.
// + - 연산자 이용
val copyFruits = fruits + "Orange"
println(fruits)
println(copyFruits)

// 결론
// 이뮤터블 리스트를 사용하자. 즉, mutableListOf(), arrayListOf() < listOf()
