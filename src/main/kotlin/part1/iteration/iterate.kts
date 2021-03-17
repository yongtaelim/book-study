val array = arrayOf(1, 2, 3)

for (e in array) {
    print("$e, ")
}

val names = listOf("Tom", "Jerry", "Spike")
for (index in names.indices) {
    println("Positoin of ${names.get(index)} is $index")
}

// 구조분해
for ((index, name) in names.withIndex()) {
    println("Position of $name is $index")
}