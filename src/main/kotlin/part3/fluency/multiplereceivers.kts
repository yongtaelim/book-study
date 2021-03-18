fun top(func: String.() -> Unit) = "hello".func()
fun nested(func: Int.() -> Unit) = (-2).func()
top {
    println("In outer lambda $this and $length")
    nested {
        println("in inner lambda $this and ${toDouble()}")
        println("from inner through receiver of outer: ${length}")
        println("from inner to outer receiver ${this@top}")
    }
}

/*
In outer lambda hello and 5
in inner lambda -2 and -2.0
from inner through receiver of outer: 5
from inner to outer receiver hello
 */