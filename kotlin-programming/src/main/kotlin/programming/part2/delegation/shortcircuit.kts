// 온도를 얻을 수 있는 외부 함수가 있다고 가정!!
fun getTemperature(city: String): Double {
    println("fetch from webservice for $city")
    return 30.0
}

val showTemperature = false
val city = "Boulder"
if (showTemperature && getTemperature(city) > 20)
    println("Warm")
else
    println("Nothing to report")

// 위 코드를 효율성 떨어지게 작성한 예시다.
val temperature = getTemperature(city) // else 일 경우에도 호출한다.
if (showTemperature && temperature > 20)
    println("Warm")
else
    println("Nothing to report")

/**
 * 위처럼 효율성 떨어지는 코드는 lazy로 대체한다.
 * lazy를 사용하면 해당 코드가 필요한 시점에서 실행된다.
 * 아래의 코드같은 경우에는 showTemperature가 true일 경우에만 실행된다.
 */

val temperature1 by lazy { getTemperature(city) }
if (showTemperature && temperature1 > 20)
    println("Warm")
else
    println("Nothing to report")