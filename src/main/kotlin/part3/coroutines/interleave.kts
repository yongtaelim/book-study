import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.yield

/*
코틀린의 코루틴 라이브러리는 서스펜션 포인트(중단점)을 포함한다.
- 서스펜션 포인트란 현재 실행중인 작업을 중지(suspend) 시키고 다른 작업을 실행시키는 함수.

- delay() : 현재 실행중인 작업을 지정된 밀리초만큼 멈추게 한다.
- yield() : 명시적인 지연을 만들지 않는다. , 현재 더 중요한 작업들의 실행을 기다린다.

- 코틀린은 suspend 키워드로 어노테이트된 함수에서만 서스펜션 포인트를 사용할 권한을 준다.
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

println("start")
runBlocking {
    launch { task1() }
    launch { task2() }
    println("called task1 and task2 from ${Thread.currentThread()}")
}
println("done")


/*
kotlinc-jvm -classpath /Users/limyongtae/.gradle/caches/modules-2/files-2.1/org.jetbrains.kotlinx/kotlinx-coroutines-core/1.2.2/6ff48bdfc38a8c22e3fc37605b6a6afaed3b6dbd/kotlinx-coroutines-core-1.2.2.jar -script interleave.kts

start
called task1 and task2 from Thread[main,5,main]
start task1 in Thread Thread[main,5,main]
start task2 in Thread Thread[main,5,main]
end task1 in Thread Thread[main,5,main]
end task2 in Thread Thread[main,5,main]
done

 */

/*
yield는 어디에 쓰일까?

작업들이 사용하는 공유자원의 경쟁 때문에 병렬로 실행시킬 수 없는 여러 개의 작업이 있다고 가정!!
작업을 순차적으로 하나씩 시키는 것은 몇몇 작업을 제외하고 다른 작업들은 전부다 자원을 사용하지 못한다!
순차적 실행은 작업이 아주 길거나 끝나지 않는 작업인 경우엔 특히 적합하지 않다.
이럴 경우엔 코루틴을 사용하면 어러개의 작업들이 상호 협력적으로 실행시킬 수 있기 때문에 모든 작업을 안정적으로 진행할 수 있따.
 */


runBlocking {
    launch(Dispatchers.Default) { task1() }
    launch { task2() }
    println("called task1 and task2 from ${Thread.currentThread()}")
}


/*
start
start task1 in Thread Thread[DefaultDispatcher-worker-1,5,main]
end task1 in Thread Thread[DefaultDispatcher-worker-3,5,main]
called task1 and task2 from Thread[main,5,main]
start task2 in Thread Thread[main,5,main]
end task2 in Thread Thread[main,5,main]
done
 */

/*
Dispathcers.Default를 선언한 task1은 다른 스레드에서 실행된다.
 */