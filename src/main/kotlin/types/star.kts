// 스타 프로젝션
// java의 ?와 비슷하다.
// 스타 프로젝션을 사용하면 읽기만 가능하고 쓰기는 불가능하다.
// * = out T
fun printValues(values: Array<*>) {
    for (value in values) {
        println(value)
    }
}

printValues(arrayOf(1,2))