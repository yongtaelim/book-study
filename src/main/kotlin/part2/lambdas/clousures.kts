
val doubleIt = { e: Int -> e * 2 }

/*
 외부 상태에 의존하는 람다 생성.
 이것을 클로저라고 부른다.
 */
val factor = 2
val doubleIts = { e: Int -> e * factor }

/*
 렉시컬 스코핑이란 무엇일까?
 위 소스에서 factor를 찾아야한다. 하지만 못찾는다면 스코프를 확장하고 못찾으면 또 확장하고.. 반복하는 행위를 말한다.
 아래의 코드에서 length 파라미터의 값은 존재하지 않는다. 스코프를 확장하여 찾아야한다. 이걸말한다.
 */
fun predicationOfLength(length: Int): (String) -> Boolean {
    return { input: String -> input.length == length }
}