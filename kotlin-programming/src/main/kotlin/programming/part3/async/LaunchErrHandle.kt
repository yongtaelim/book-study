package org.kotlinlang.play.part3.async

import kotlinx.coroutines.*

fun main() = runBlocking {
    val handler = CoroutineExceptionHandler { context, ex ->
        println("Caught: ${context[CoroutineName]} ${ex.message?.substring(0..28)}")
    }

    try {
        val airportCodes = listOf("LAX", "SF-", "PD-", "SEA")
        val jobs: List<Job> = airportCodes.map { anAirportCode ->
            launch(Dispatchers.IO + CoroutineName(anAirportCode) + handler + SupervisorJob()) {
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
Caught: CoroutineName(SF-) Unable to instantiate Airport
Caught: CoroutineName(PD-) Unable to instantiate Airport
SEA delay: false
LAX delay: false
Cancelled: false
Cancelled: true
Cancelled: true
Cancelled: false
 */