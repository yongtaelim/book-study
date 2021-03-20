fun compute(n: Int) = 0

println(compute(4))


// kotlinc -script warning.kts
// warning.kts:1:13: warning: parameter 'n' is never used
// fun compute(n:Int) = 0
// ^

// kotlinc -Werror -script warning.kts
//warning: parameter 'n' is never used (warning.kts:1:13)
//error: warnings found and -Werror specified
//        warning.kts:1:13: warning: parameter 'n' is never used
//fun compute(n:Int) = 0
//^
