println("hi" == "hi")
println("hi" == "Hi")
println(null == "hi")
println("hi" == "hi")
println(null == null)

//true
//false
//false
//true
//true
//equals.kts:3:9: warning: condition 'null == "hi"' is always 'false'
//println(null == "hi")
//^
//equals.kts:5:9: warning: condition 'null == null' is always 'true'
//println(null == null)
//^
// NullPointException이 없다.