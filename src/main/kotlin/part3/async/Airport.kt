package org.kotlinlang.play.part3.async

import com.beust.klaxon.Json
import com.beust.klaxon.Klaxon
import java.net.URL
import java.text.NumberFormat

/*
implementation("com.beust:klaxon:5.5")
 */

class Weather(@Json(name = "Temp") val temperature: Array<String>)
class Airport(
    @Json(name = "IATA") val code: String,
    @Json(name = "Name") val name: String,
    @Json(name = "Delay") val delay: Boolean,
    @Json(name = "Weather") val weather: Weather
) {
    companion object {
        fun getAirportData(code: String): Airport? {
            val url = "https://soa.smext.faa.gov/asws/api/airport/status/$code"
            return Klaxon().parse<Airport>(URL(url).readText())
        }
    }
}

/*
Airport 클래스에서 @Json 을 이용하여 JSON 응답이 클래스내의 프로퍼티에 맵핑되도록 한다.
getAirportData() 메소드에서 우리는 데이터를 가지고 오고, text 응답을 추출한 다음 JSON 컨텐츠를 Airport의 인스턴스로 parse한다.
 */

/*
Code      Temperature         Delay
LAX       54.0 F (12.2 C)     false
SFO       55.0 F (12.8 C)     false
PDX       46.0 F (7.8 C)      false
SEA       46.0 F (7.8 C)      false
Time taken 2548 ms
 */