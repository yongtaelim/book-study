fun oddOrEven(number: Int): String {
    if (number % 15 == 0)
        return "odd1"
    else if (number % 5 == 0)
        return "odd2"
    else if (number % 3 == 0)
        return "odd3"
    else
        return "even"
}

fun oddOrEven(number: Int): String {
    return if (number % 15 == 0)
         "odd1"
    else if (number % 5 == 0)
         "odd2"
    else if (number % 3 == 0)
         "odd3"
    else
         "even"
}

fun oddOrEven(number: Int) =
    if (number % 15 == 0)
        "odd1"
    else if (number % 5 == 0)
        "odd2"
    else if (number % 3 == 0)
        "odd3"
    else
        "even"

fun oddOrEven(number: Int) =
    when {
        number % 15 == 0 -> "odd1"
        number % 5 == 0 -> "odd2"
        number % 3 == 0 -> "odd3"
        else -> "even"
    }

fun oddOrEven(number: Int) =
    try {
        number
    } catch (ex: Exception) {
        0
    }