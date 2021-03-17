import java.lang.StringBuilder

fun fetchMessage(id: Int): Any =
    if (id == 1) "Record found" else StringBuilder("data not found")

// error 발생 코드.
// StringBuilder는 String으로 캐스트되지 않는다.
for (id in 1..2) {
    println("Message length: ${(fetchMessage(id) as String).length}")
}

// refectoring
for (id in 1..2) {
    println("Message length: ${(fetchMessage(id) as? String)?.length ?: "---"}")
}

// as보다는 as? 를 사용하자.
// 가능한 스마트 캐스트를 사용하자.