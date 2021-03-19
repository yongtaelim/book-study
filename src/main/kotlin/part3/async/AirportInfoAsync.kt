package org.kotlinlang.play.part3.async

import kotlinx.coroutines.*
import kotlin.system.measureTimeMillis

fun main()  = runBlocking {
    val format = "%-10s%-20s%-10s"
    println(String.format(format, "Code", "Temperature", "Delay"))
    val time = measureTimeMillis {
        val airportCodes = listOf("LAX", "SFO", "PDX", "SEA")
        val airportData: List<Deferred<Airport?>> =
            airportCodes.map { anAirportCode ->
                async(Dispatchers.IO) {
                    Airport.getAirportData(anAirportCode)
                }
            }

        airportData
            .mapNotNull { anAirportData -> anAirportData.await() }
            .forEach { anAirport ->
                println(String.format(format, anAirport.code, anAirport.weather.temperature[0], anAirport.delay))
            }
    }

    println("Time taken $time ms")
}

/*
Code      Temperature         Delay
LAX       53.0 F (11.7 C)     false
SFO       54.0 F (12.2 C)     false
PDX       47.0 F (8.3 C)      false
SEA       44.0 F (6.7 C)      false
Time taken 2820 ms
 */

/*
비동기로 작성한 프로그램이다.
runBlocking을 사용
첫번째 루프에서 async를 사용하였습니다. async의 return값은 Deferred입니다.

두 번째 루프에서 Deferred 리스트를 반복하면서 await()를 실행하여 데이터를 가져왔습니다.
await()를 호출하면 실행의 흐름을 차단하지만 실행의 스레드는 차단하지 않습니다.
async()를 아규먼트 없이 바로 사용했기 때문에 main 스레드는 await() 호출에 도달한 이후에 코루틴을 실행시킨다.

이번 케이스는 async()를 Dispatchers.IO 컨텍스트에서 사용했기 때문에 Dispatchers.IO 풀의 스레드가 웹 서비스에 요청을 하는 동안 main 스레드가 쉬고 있다.


 */