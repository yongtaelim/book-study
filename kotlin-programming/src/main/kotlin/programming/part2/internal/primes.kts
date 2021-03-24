package org.kotlinlang.play.part2.internal

/*
코틀린에서 내부 반복자는 콜렉션 사이즈가 작을 때 사용하자!
사이즈가 큰 콜렉션에는 시퀀스를 이용해서 내부 반봅자를 사용해야한다.
 이유 ) 콜렉션의 연산이 계속 실행되는 것과는 다르게 시퀀스에서 호출되는 함수는 지연되어 실행된다. 즉 연산결과가 필요하지 않을때는 연산하지 않는다.
 */
data class Person(val firstName: String, val age: Int)
// 노가다...
val people = listOf(
    Person("A", 10),
    Person("B", 11),
    Person("A", 12),
    Person("D", 13),
    Person("E", 14),
    Person("F", 21),
    Person("G", 26),
    Person("A", 27),
    Person("I", 28)
)

fun isAdult(person: Person): Boolean {
    println("isAdult called for ${person.firstName}")
    return person.age > 17
}

fun fetchFirstName(person: Person): String {
    println("fetchFirstName called for ${person.firstName}")
    return person.firstName
}

// 시퀀스 연산자가 아닌경우 정말 많은 일을 한다.
val nameOfFirstAdult = people
    .filter(::isAdult)
    .map(::fetchFirstName)
    .first()

println(nameOfFirstAdult)

/*
isAdult called for A
isAdult called for B
isAdult called for A
isAdult called for D
isAdult called for E
isAdult called for F
isAdult called for G
isAdult called for A
isAdult called for I
fetchFirstName called for F
fetchFirstName called for G
fetchFirstName called for A
fetchFirstName called for I
F
 */

val nameOfFirstAdult1 = people.asSequence()
    .filter(::isAdult)
    .map(::fetchFirstName)
    .first()

println(nameOfFirstAdult1)

/*
 시퀀스는 일을 적게 하네? 그럼 모든 내부 반복자에 사용하면 되... 안된다!
 콜렉션이 작을 경우 퍼포먼스 차이는 무시할 정도이다. 이런 경우 지연 연산을 사용하지 않는 것이 디버그가 편리하고 추론하기 쉽다.
 */

// 무한 시퀀스..
// 무슨말인지 모르겠다!
//fun isPrive(n: Long) = n > 1 && (2 until n).none { i -> n % i == 0L }