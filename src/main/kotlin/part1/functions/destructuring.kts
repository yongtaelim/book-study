fun getFullName() = Triple("John", "Quncy", "Adams")

// bad!!
val result = getFullName()
val first = result.first
val second = result.second
val third = result.third
println("$first $second $third")

// Good!!
val (s1, s2, s3) = getFullName()
println("$s1 $s2 $s3")

// skip!!
val (_, _, s4) = getFullName()
