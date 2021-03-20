import kotlinx.coroutines.*
import java.net.URL

// 코틀린 취소
suspend fun compute(checkActive: Boolean) = coroutineScope {
    var count = 0L
    val max = 10000000000

    while (if (checkActive) {
            isActive
        } else (count < max)
    ) {
        count++
    }
    if (count == max) {
        println("compute, checkActive $checkActive ignored cancellation")
    } else {
        println("compute, checkActive $checkActive bailed out early")
    }
}

val url = "http://httpstat.us/200?sleep=2000"
fun getResponse() = URL(url).readText()
suspend fun fetchResponse(callAsync: Boolean) = coroutineScope {
    try {
        val response = if (callAsync) {
            async { getResponse() }.await() // 취소 허용 O
        } else {
            getResponse()  // 취소 허용 X
        }
        println(response)
    } catch (e: CancellationException) {
        println("fetchResponse called with callAsync $callAsync: ${e.message}")
    }
}

runBlocking {
    val job = launch(Dispatchers.Default) {
        launch { compute(checkActive = false) }       // 방해 실패
        launch { compute(checkActive = true) }        // 방해 성공
        launch { fetchResponse(callAsync = false) }   // 방해 실패
        launch { fetchResponse(callAsync = true) }    // 방해 성공
    }
    println("Let them run...")
    Thread.sleep(1000)
    println("OK, that's enough, cancel")
    job.cancelAndJoin()
}

/*
kotlinc-jvm -classpath /Users/limyongtae/.gradle/caches/modules-2/files-2.1/org.jetbrains.kotlinx/kotlinx-coroutines-core/1.2.2/6ff48bdfc38a8c22e3fc37605b6a6afaed3b6dbd/kotlinx-coroutines-core-1.2.2.jar -script
 */

/*
Let them run...
OK, that's enough, cancel
compute, checkActive true bailed out early
fetchResponse called with callAsync true: Job was cancelled
200 OK
compute, checkActive false ignored cancellation
 */

/*
compute는 isActive 체크에 의해서 빠르게 종료된다.
fetchResponse도 비슷하다.
 */