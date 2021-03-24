package org.kotlinlang.play.part3.async

import kotlinx.coroutines.*
import java.net.URL

// 이전 코드에서 supervisor를 사용하도록 변경하자
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
        supervisorScope {
            launch { fetchResponse(200, 5000) }
            launch { fetchResponse(202, 1000) }
            launch { fetchResponse(404, 2000) }
        }

    }
    Thread.sleep(4000)
    println("200 should still be running at this time")
    println("let the parent cancel now")
    job.cancel()
    job.join()
}

/*
202 Accepted
Exception handled: http://httpstat.us/404?sleep=2000
200 should still be running at this time
let the parent cancel now
Job was cancelled for fetchResponse 200
 */

/*
launch() 호출을 supervisorScope로 감쌌다.
로그를 살펴보자!
1. 202코드를 처리하는 코루틴은 완료!
2. 404코드를 처리하는 코루틴은 Exception으로 취소!
3. 200코드를 처리하는 코루틴은 이 시간 동안 영향을 받지 않고 5초가 되면 완료를 칠것이다!!
4. 하지만 4초 후 부모 코루틴을 취소시키면서 자식 코루틴도 같이 취소하게 되면서 결국 완료하지 못함
 */

/*
명확하게 독립된 작업을 하는 자식 코루틴의 탑다운 계층구조를 구성하고 싶을때만 코루틴에 슈퍼바이저를 적용해야한다.
위와 같은 케이스를 말한다. 부모 코루틴이 취소되면 자식도 취소된다.

반면에 완전히 협력이 필요하다면 코루틴의 기본(default) 동작에 의존하자!
 */