import kotlinx.coroutines.*
import java.net.URL

suspend fun fetchResponse(code: Int, delay: Int) = coroutineScope {
    try {
        val response = async {
            URL("http://httpstat.us/$code?sleep=$delay").readText()
        }.await()
        println(response)
    } catch (e: CancellationException) {
        println("${e.message} for fetchResponse $code")
    }
}

runBlocking {
    val handler = CoroutineExceptionHandler { _, ex ->
        println("Exception handled: ${ex.message}")
    }

    val job = launch(Dispatchers.IO + SupervisorJob() + handler) {
        launch { fetchResponse(200, 5000) }
        launch { fetchResponse(202, 1000) }
        launch { fetchResponse(404, 2000) }
    }
    job.join()
}

/*
202 Accepted
Parent job is Cancelling for fetchResponse 200
Exception handled: http://httpstat.us/404?sleep=2000
 */

/*
코루틴은 cancellationException이 아닌 다른 예외가 발생하면 코루틴을 중지시킨다.
로그 살펴보자
 1. 202는 성공
 2. 404를 처리하던 중 Exception이 발생
 3. 200은 성공할 수 있었는데....ㅠ 코루틴이 종료되면서 자식 코루틴도 같이 종료된다.
 */