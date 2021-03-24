import kotlin.properties.Delegates.vetoable

/**
 * vetoable 을 사용하면 observable과는 다르게 return값이 boolean으로 존재한다.
 * return 값이 true일 경우에만 값을 변경한다.
 */

var count by vetoable(0) { _, oldValue, newValue -> newValue > oldValue }

println("The value of count is: $count")
count++
println("The value of count is: $count")
count--
println("The value of count is: $count")

/*
The value of count is: 0
The value of count is: 1
The value of count is: 1
 */