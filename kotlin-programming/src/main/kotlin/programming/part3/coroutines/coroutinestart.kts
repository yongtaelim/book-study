import kotlinx.coroutines.*
import java.util.concurrent.Executors

/*
launch()의 두번째 옵셔널 전달자인 CoroutineStart 타입 옵셔널 전달인자 값을 DEFAULT로 설정!

- CoroutineStart엔 DEFAULT, LAZY, ATOMIC, UNDISPATCHED 중 선택 가능
- LAZY : 명시적으로 start()가 호출되기 전까지 실행 연기
- ATOMIC : 중단할 수 없는 모드로 실행
- UNDISPATCHED : 처음엔 현재 컨텍스트에서 실행되지만 서스펜션 포인트 이후엔 스레드를 스위치해서 실행
 */

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

Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors())
    .asCoroutineDispatcher().use { context ->
        println("start")
        runBlocking {
            @UseExperimental(ExperimentalCoroutinesApi::class)
            launch(context = context, start = CoroutineStart.UNDISPATCHED) { task1() }
            launch { task2() }
            println("called task1 and task2 from ${Thread.currentThread()}")
        }
        println("done")
    }

/*
kotlinc-jvm -Xuse-experimental=kotlin.Experimental -classpath /Users/limyongtae/.gradle/caches/modules-2/files-2.1/org.jetbrains.kotlinx/kotlinx-coroutines-core/1.2.2/6ff48bdfc38a8c22e3fc37605b6a6afaed3b6dbd/kotlinx-coroutines-core-1.2.2.jar -script coroutinestart.kts
 */

/*
start
start task1 in Thread Thread[main,5,main]
end task1 in Thread Thread[pool-1-thread-1,5,main]
called task1 and task2 from Thread[main,5,main]
start task2 in Thread Thread[main,5,main]
end task2 in Thread Thread[main,5,main]
done
 */

/*
task1 시작 -> main
task1 서스펜션 포인드 ( yield() )
task1 end -> pool-1-thread-1
 */