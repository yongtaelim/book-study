import kotlin.properties.Delegates.observable

// 모니터링할 때 유용할 듯?
var count by observable(0) { property, oldValue, newValue -> println("Property: $property OldValue: $oldValue NewValue: $newValue") }

println("The value of count is: $count")
count++
println("The value of count is: $count")
count--
println("The value of count is: $count")

/*
The value of count is: 0
Property: var Observe.count: kotlin.Int OldValue: 0 NewValue: 1
The value of count is: 1
Property: var Observe.count: kotlin.Int OldValue: 1 NewValue: 0
The value of count is: 0
 */