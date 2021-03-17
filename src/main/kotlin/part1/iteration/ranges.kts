val oneToFive: IntRange = 1..5
val aToE: CharRange = 'a'..'e'
val seekHelp: ClosedRange<String> = "hell".."help"

println(seekHelp.contains("helm"))
println(seekHelp.contains("helq"))

for (i in oneToFive) {  // val i  var x
    println("$i, ")
}

for (ch in aToE) {
    println("$ch, ")
}

// 안된다!!
// ClosedRange<T>에는 iterator 함수가 없다.
//for (word in seekHelp) {
//
//}