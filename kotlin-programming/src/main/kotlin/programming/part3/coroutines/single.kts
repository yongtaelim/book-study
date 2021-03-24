import kotlinx.coroutines.asCoroutineDispatcher
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.yield
import java.util.concurrent.Executors

suspend fun task1() {
    println("start task1 in Thread ${Thread.currentThread()}")
    yield()
    println("end task1 in Thread ${Thread.currentThread()}")
}

suspend fun task2() {
    println("start task2 in Thread ${Thread.currentThread()}")
    yield()
    println("end task2 in Thread ${Thread.currentThread()}")
}

/*
싱글스레드 실행자에서 디스패처를 만들고 launch()에 직접 전달하고 싶다!
이 부분에 대해서는 주의사항이 있다. 실행자를 닫지 않으면 프로그램이 영원히 멈추지 않는다.
이유 : 실행자의 풀에는 main 스레드 외에도 액티브 스레드가 있고 액티브 스레드가 JVM을 계속 살려둔다.
이것을 방지하기 위해 use() 메소드를 사용한다. Java의 try-with-resources 기능과 유사하다.
 */

Executors.newSingleThreadExecutor().asCoroutineDispatcher().use { context ->
    println("start")
    runBlocking {
        launch(context) { task1() }
        launch { task2() }
        println("called task1 and task2 from ${Thread.currentThread()}")
    }
    println("done")
}

/*
start
start task1 in Thread Thread[pool-1-thread-1,5,main]
end task1 in Thread Thread[pool-1-thread-1,5,main]
called task1 and task2 from Thread[main,5,main]
start task2 in Thread Thread[main,5,main]
end task2 in Thread Thread[main,5,main]
done
 */

/*
task1 코루틴은 싱글스레드 내에서 작동하게 된다.
 */

/*
 single 스레드가 아닌 멀티플 스레드를 가지는 풀을 사용하고 싶어!!!
 아래 처럼 작성하면 시스템의 코어 숫자와 동일한 스레드 숫자를 가지는 커스텀 풀에서 실행!
 */

Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors()).asCoroutineDispatcher().use { context ->
    println("start")
    runBlocking {
        launch(context) { task1() }
        launch { task2() }
        println("called task1 and task2 from ${Thread.currentThread()}")
    }
    println("done")
}