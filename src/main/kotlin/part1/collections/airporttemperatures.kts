import kotlin.math.roundToInt


// 함수형 스타일 map() 반복자를 이용
// (코드, 온도) Pair의 리스트
val airportCodes = listOf("LAX", "SFO", "PDX", "SEA")
val temperatures = airportCodes.map { code -> code to getTemperatureAtAirport(code) }

for (temperature in temperatures) {
    println("Airport: ${temperature.first}: Temperature: ${temperature.second}")
}

fun getTemperatureAtAirport(code: String): String = "${(Math.random() * 30).roundToInt() + code.count()} C"

// 이뮤터블 - Pair, Triple.. 2 ~ 3 개 변수를 구성할 떄 좋음. 4개 이상일때는 data 객체를 고려...
