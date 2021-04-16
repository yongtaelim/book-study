// bad!!
fun isAlive(alive: Boolean, numberOfLiveNeighbors: Int): Boolean {
    if (numberOfLiveNeighbors < 2) {
        return false
    }
    if (numberOfLiveNeighbors > 3) {
        return false
    }
    if (numberOfLiveNeighbors == 3) {
        return true
    }
    return alive && numberOfLiveNeighbors == 2
}

// good!!
fun isAliveGood(alive: Boolean, numberOfLiveNeighbors: Int) = when {
    numberOfLiveNeighbors < 2 -> false
    numberOfLiveNeighbors > 3 -> false
    numberOfLiveNeighbors == 3 -> true
    else -> alive && numberOfLiveNeighbors == 2
}

fun test() {
    var list = listOf(1,2,3)

    print(when (list.size) {
        3, 4, 5 -> true
        else -> false
    })
}

fun whatToDo(dayOfWeek: Any) = when (dayOfWeek) {
    "Saturday", "Sunday" -> "Relax"
    in listOf("Monday", "Tuesday", "Wednesday", "Thursday") -> "Work hard"
    in 2..4 -> "Work hard"
    "Friday" -> "Party"
    is String -> "What?"
    else -> "No clue"
}

println(whatToDo("Sunday"))  // Relax
println(whatToDo("Wednesday"))  // Work hard
println(whatToDo(3))  // Work hard
println(whatToDo("Friday"))  // Party
println(whatToDo("Munday"))  // What?
println(whatToDo(8))  // No clue

// 주의 할점!!
// when에서 -> 뒤에 블록이 올 수 있다. 하지만 되도록이면 쓰지 말자!! 너무 복잡해진다. 메서드로 빼도록 하자

// not bad.. not good..
fun systemInfo1(): String {
    val numberOfCores = Runtime.getRuntime().availableProcessors()
    return when (numberOfCores) {
        1 -> "1 core, packing....."
        in 2..16 -> "You have $numberOfCores cores"
        else -> "$numberOfCores cores!, I wan..."
    }
}

// good!!
fun systemInfo2(): String {
    return when (val numberOfCores = Runtime.getRuntime().availableProcessors()) {
        1 -> "1 core, packing....."
        in 2..16 -> "You have $numberOfCores cores"
        else -> "$numberOfCores cores!, I wan..."
    }
}