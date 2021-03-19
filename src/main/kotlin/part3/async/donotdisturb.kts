import kotlinx.coroutines.*

suspend fun doWork(id: Int, sleep: Long) = coroutineScope {
    try {
        println("$id: entered $sleep")
        delay(sleep)

        println("$id: finished nap $sleep")
        withContext(NonCancellable) {
            println("$id: do not distrub, please")
            delay(5000)
            println("$id: OK, you can talk to me now")
        }
        println("$id: outside the restricted context")
        println("$id: isActive: $isActive")
    } catch (e: CancellationException) {
        println("$id: doWork($sleep) was cancelled")
    }
}

/*
1. doWork 실행
2. 첫번째 delay에 걸리면 취소 가능
3. withContext(NonCancellable) 부터 취소 불가능
4. 두번째 delay에 걸려도 취소 불가능 - 취소 명령이 들어오면 isActive 프로퍼티 변경
5. isActive 프로퍼티 프린트
 */

runBlocking {
    val job = launch(Dispatchers.Default) {
        launch { doWork(1, 3000) }
        launch { doWork(2, 1000) }
    }
    Thread.sleep(2000)
    job.cancel()
    println("cancelling")
    job.join()
    println("done")
}

/*
1: entered 3000
2: entered 1000
2: finished nap 1000
2: do not distrub, please
cancelling
1: doWork(3000) was cancelled
2: OK, you can talk to me now
2: outside the restricted context
2: isActive: false
done
 */