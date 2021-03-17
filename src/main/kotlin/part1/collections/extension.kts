val names = listOf("Tom", "Jerry")
println(names.javaClass)

for ((index, name) in names.withIndex()) {
    println("$index, $name")
}