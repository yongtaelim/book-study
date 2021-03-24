package org.kotlinlang.play.part3.async

import kotlinx.coroutines.*

fun main() = runBlocking {
    val airportCodes = listOf("LAX", "SF-", "PD-", "SEA")
    val airportData = airportCodes.map { anAirportCode ->
        async(Dispatchers.IO + SupervisorJob()) {
            Airport.getAirportData(anAirportCode)
        }
    }

    for (anAirportData in airportData) {
        try {
            val airport = anAirportData.await()
            println("${airport?.code} ${airport?.delay}")
        } catch (e: Exception) {
            println("Error: ${e.message?.substring(0..28)}")
        }
    }
}

// async와 Exception

/*
LAX false
Error: Unable to instantiate Airport
Error: Unable to instantiate Airport
SEA false
 */

/*
함수형 스타일인 forEach()가 아니고 명령형 스타일의 for 반복문을 사용했다.
람다를 써서 예외처리를 하면 코드가 복잡해진다.. 여기서는 명령형 스타일의 for문이 좋다!
 */

/*
이해를 돕기 위해 Async와 Launch를 비교하면서 살펴보자!
 */