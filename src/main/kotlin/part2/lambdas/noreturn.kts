// 라벨 리턴, 논로컬 리턴
fun invokeWith(n: Int, action: (Int) -> Unit) {
    println("enter invokeWith $n")
    action(n)
    println("exit invokeWith $n")
}

/*
lambda에서는 return이 없다.
 */
//fun caller() {
//    (1..3).forEach { i ->
//        invokeWith(i) {
//            println("enter for $it")
//
//            if (it == 2) {
//                return
//            }
//
//            println("exit for $it")
//        }
//    }
//}
//

/*
라벨 리턴
here@, @here을 사용하자! 리턴하면 여기로 오세요 ~
for문에서 continue와 동일하다.
 */

fun caller() {
    (1..3).forEach { i ->
        invokeWith(i) here@ {
            println("enter for $it")

            if (it == 2) {
                return@here
            }

            println("exit for $it")
        }
    }
}

//caller()
//println("after return from caller")

fun caller1() {
    (1..3).forEach { i ->
        invokeWith(i) {
            println("enter for $it")

            if (it == 2) {
                return@invokeWith
            }

            println("exit for $it")
        }
    }
}

caller1()
println("after return from caller")
/*
enter invokeWith 1
enter for 1
exit for 1
exit invokeWith 1
enter invokeWith 2
enter for 2
exit invokeWith 2
enter invokeWith 3
enter for 3
exit for 3
exit invokeWith 3
after return from caller
 */

/*
 논로컬 리턴
 람다에서는 return 안된다매!!
 ...
 논로컬 리턴을 사용하면 현재 동작중인 람다를 선언한 곳 바깥으로 나간다. 하지만 람다를 받은 함수가 inline으로 선언된 경우만 사용 가능.
 */

fun caller2() {
    (1..3).forEach { i ->
        println("in forEach for $i")
        if (i == 2) { return }
        invokeWith(i) {
            println("enter for $it")

            if (it == 2) {
                return@invokeWith
            }

            println("exit for $it")
        }
    }
}

caller2()
println("after return from caller")

/*

in forEach for 1
enter invokeWith 1
enter for 1
exit for 1
exit invokeWith 1
in forEach for 2
after return from caller

 */