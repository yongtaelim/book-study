package org.kotlinlang.play.part3.async

import kotlinx.coroutines.*

fun main() = runBlocking {
    try {
        val airportCodes = listOf("LAX", "SF-", "PD-", "SEA")
        val jobs: List<Job> = airportCodes.map { anAirportCode ->
            launch(Dispatchers.IO + SupervisorJob()) {
                val airport = Airport.getAirportData(anAirportCode)
                println("${airport?.code} delay: ${airport?.delay}")
            }
        }
        jobs.forEach { it.join() }
        jobs.forEach { println("Cancelled: ${it.isCancelled}") }
    } catch (e: Exception) {
        println("ERROR: ${e.message}")
    }
}

/*
예외를 다루는 예제 작성

async() 사용하지 않고 launch()를 사용하여 알아보자
launch() 는 코루틴이 시작됐다는 의미를 가지는 Job 객체를 리턴한다.
Job객체의 isCancelled 프로퍼티를 이용해서 작업이 성공적으로 완료되었는지 아니면 실패가 일어나서 취소되었는지 확인하자!
 */

/*
SEA delay: false
LAX delay: false
Cancelled: false
Cancelled: true
Cancelled: true
Cancelled: false

그리고 Exception.....
 */

/*
안타깝게도 try..catch로는 잡히지 않는다 ㅜㅜㅜㅜㅜ
이유는 코루틴이 launch()를 이용해서 실행되었기 때문에 예외를 호출자에게까지 전파하지 못한다.
launch()의 특성이다! 하지만 launch()를 사용하더라도 잘 써야한다!

launch()를 사용한다면, 예외 핸들러를 반드시 설정해야한다.
CoroutineExceptionHandler 예외 핸들러가 등록되어 있다면 컨텍스트 세부사항과 예외 정보와 함께 핸들러가 트리거된다.
 */