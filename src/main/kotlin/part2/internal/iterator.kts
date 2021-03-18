data class Person(val firstName: String, val age: Int)

// 노가다...
val people = listOf(
    Person("A", 10),
    Person("B", 11),
    Person("A", 12),
    Person("D", 13),
    Person("E", 14),
    Person("F", 15),
    Person("G", 16),
    Person("A", 17),
    Person("I", 18),
)

// Sort
val namesSortedByAge = people.filter { person -> person.age > 15 }
    .sortedBy { person -> person.age }
//    .sortedByDescending { person -> person.age }
    .map { person -> person.firstName }

// Group
val groupBy1stLetter = people.groupBy { person -> person.firstName.first() }
println(groupBy1stLetter)

