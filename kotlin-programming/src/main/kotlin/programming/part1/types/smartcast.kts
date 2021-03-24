// 여기에 스마트 캐스트를 적용해보자.
fun WhatToDo1(dayOfWeek: Any) = when (dayOfWeek) {
    "Saturday", "Sunday" -> "Relax"
    in listOf("Monday", "TuesDay", "Wednesday", "Thursday") -> "Work hard"
    in 2..4 -> "Work hard"
    "Friday" -> "Party"
    is String -> "What???"
    else -> "No clue"
}

// Apply
fun WhatToDo2(dayOfWeek: Any) = when (dayOfWeek) {
    "Saturday", "Sunday" -> "Relax"
    in listOf("Monday", "TuesDay", "Wednesday", "Thursday") -> "Work hard"
    in 2..4 -> "Work hard"
    "Friday" -> "Party"
    is String -> "What, .... length ${dayOfWeek.length}"
    else -> "No clue"
}