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

    val job = launch(Dispatchers.IO + handler) {
        withTimeout(3000) {
            launch { fetchResponse(200, 5000) }
            launch { fetchResponse(201, 1000) }
            launch { fetchResponse(202, 2000) }
        }

    }
    job.join()
}

/*
201 Created
Timed out waiting for 3000 ms for fetchResponse 200
Timed out waiting for 3000 ms for fetchResponse 202
 */

/*
이번 코드는 타임아웃이 없다면 모두 성공해야한다.
하지만 로그를 살펴보면 설정한 시간을 초과하는 요청은 모두 실패한다!
 */