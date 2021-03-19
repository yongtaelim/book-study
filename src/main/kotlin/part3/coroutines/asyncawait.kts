import kotlinx.coroutines.Deferred
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.runBlocking

runBlocking {
    val count: Deferred<Int> = async(Dispatchers.Default) {
        println("fetching in ${Thread.currentThread()}")
        Runtime.getRuntime().availableProcessors()
    }

    println("Called the function in ${Thread.currentThread()}")
    println("Number of cores is ${count.await()}")
}

/*
- 싱글 스레드
- 코루틴은 호출자와 동일한 스레드에서 동작

1. main 쓰레드가 async() 호출 이후에 print문을 실행시킨다.
2. await() 메소드를 호출하면 async()에 의해서 실행된 코루틴이 완료되기를 기다리게 만든다.
3. 마지막 print문은 코루틴의 응답을 받은 후 출력한다.
 */

/*
Called the function in Thread[main,5,main]
fetching in Thread[DefaultDispatcher-worker-1,5,main]
Number of cores is 16
 */

/*
async(Dispatcher.Default)에서 async로 수정한다면 코루틴은 main에서 작동할 것이다.!!
 */

/*
kotlinc-jvm -classpath /Users/limyongtae/.gradle/caches/modules-2/files-2.1/org.jetbrains.kotlinx/kotlinx-coroutines-core/1.2.2/6ff48bdfc38a8c22e3fc37605b6a6afaed3b6dbd/kotlinx-coroutines-core-1.2.2.jar -script
 */

