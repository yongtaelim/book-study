data class Person(val firstName: String, val age: Int)

// 노가다...
val people = listOf(
    Person("AS", 10),
    Person("BW", 11),
    Person("CE", 12),
    Person("DH", 13),
    Person("EM", 14),
    Person("FH", 15),
    Person("GD", 16),
    Person("HZ", 17),
    Person("IP", 18),
)

// 이름 나열
val result = people.filter { person -> person.age > 15 }
    .map { person -> person.firstName }
    .map { name -> name.toLowerCase() }
//    .joinToString(", ")
    .reduce { names, name -> "$names, $name" }
// reduce에 전달된 람다는 줄을 뛰어넘어가면서 연산을 진행

println(result)

// 나이 합산
var totalAge = people.map { person -> person.age }
//    .sum()
    .reduce { total, age -> total + age }

println(totalAge)

// 플랫화와 플랫맵
val families = listOf(
    listOf(Person("AE", 30), Person("BH", 40)),
    listOf(Person("CS", 50), Person("DZ", 20))
)

println(families.size) // 2
println(families.flatten().size) // 4

val namesAndReversed = people.map { person -> person.firstName }
    .map(String::toLowerCase)
    .map { name -> listOf(name, name.reversed()) }
println(namesAndReversed)

val namesAndReversed2 = people.map { person -> person.firstName }
    .map(String::toLowerCase)
    .map { name -> listOf(name, name.reversed()) }
    .flatten()
println(namesAndReversed2)

val namesAndReversed3 = people.map { person -> person.firstName }
    .map(String::toLowerCase)
    .flatMap { name -> listOf(name, name.reversed()) }
println(namesAndReversed3)

/*
map과 flatMap 선택방법
- 람다가 one-to-one 함수라면 ( 1param, 1return) 콜렉션 변경을 위해서 map() 사용
- 람다가 one-to-many 함수라면 기존 콜렉션을 변경하여 콜렉션의 콜렉션으로 넣기 위해서 map()사용
- 람다가 one-to-many 함수지만 기존 콜렉션을 변경해서 객체나 값의 변경된 콜렉션으로 넣고 싶다면 flatMap 사용
 */

val personNames = people.map { person -> person.firstName }
println(personNames)  // [AS, BW, CE, DH, EM, FH, GD, HZ, IP]

val personNames2 = people.map { person -> listOf(person.firstName, "temp${person.firstName}") }
println(personNames2)  // [[AS, tempAS], [BW, tempBW], [CE, tempCE], [DH, tempDH], [EM, tempEM], [FH, tempFH], [GD, tempGD], [HZ, tempHZ], [IP, tempIP]]

val personNames3 = people.flatMap { person -> listOf(person.firstName, "temp${person.firstName}") }
println(personNames3)  // [AS, tempAS, BW, tempBW, CE, tempCE, DH, tempDH, EM, tempEM, FH, tempFH, GD, tempGD, HZ, tempHZ, IP, tempIP]
