var length = 100
val printIt: (Int) -> Unit = {
    n: Int -> println("n is $n, length is $length")
}
printIt(6)  // n is 6, length is 100

val printIts: String.(Int) -> Unit = {
    n: Int -> println("n is $n, length is $length")
}

"Hello".printIts(6)  // n is 6, length is 5