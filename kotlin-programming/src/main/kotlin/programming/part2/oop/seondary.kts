// 보조 생성자
/**
 * constructor는 선택사항이다.
 */
class Person(val first: String, val last: String) {
    var fulltime = true
    var location: String = "-"

    constructor(first: String, last: String, fte: Boolean) : this(first, last) {
        fulltime = fte
    }

    constructor(first: String, last: String, loc: String) : this(first, last, false) {
        location = loc
    }

    override fun toString(): String {
        return "person(first='$first', last='$last', fulltime=$fulltime, location='$location')"
    }
}

println(Person("Jame", "Doe"))
println(Person("Jame", "Doe", false))
println(Person("Jame", "Doe", "home"))