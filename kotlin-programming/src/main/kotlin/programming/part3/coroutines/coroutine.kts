import kotlinx.coroutines.runBlocking

/*
implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.4.3") // add
 */

fun task1() {
    println("start task1 in Thread ${Thread.currentThread()}")
    println("end task1 in Thread ${Thread.currentThread()}")
}

fun task2() {
    println("start task2 in Thread ${Thread.currentThread()}")
    println("end task2 in Thread ${Thread.currentThread()}")
}

println("start")
runBlocking {
    task1()
    task2()
    println("called task1 and task2 from ${Thread.currentThread()}")
}
println("done")


/*
kotlinc-jvm -classpath /Users/limyongtae/.gradle/caches/modules-2/files-2.1/org.jetbrains.kotlinx/kotlinx-coroutines-core/1.2.2/6ff48bdfc38a8c22e3fc37605b6a6afaed3b6dbd/kotlinx-coroutines-core-1.2.2.jar -script coroutine

start
start task1 in Thread Thread[main,5,main]
end task1 in Thread Thread[main,5,main]
start task2 in Thread Thread[main,5,main]
end task2 in Thread Thread[main,5,main]
called task1 and task2 from Thread[main,5,main]
done

람다 안의 코드는 runBlocking() 호출 이전의 코드와 해당 함수 호출 이후의 코드 사이에서 인터리브된 메인 스레드에서 실행되었다.
 */