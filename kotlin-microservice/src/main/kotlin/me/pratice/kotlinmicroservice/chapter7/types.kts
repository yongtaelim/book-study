fun lower(name: String) : String {
    val lower: String = name.toLowerCase()
    return "$name in lower case id: $lower"
}

fun lower(name: String): String {
    val lower = name.toLowerCase()
    return "$name in lower case id: $lower"
}

fun lower(name: String) = "$name in lower case id: ${name.toLowerCase()}"