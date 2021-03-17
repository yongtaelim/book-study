// 명시적 아규먼트를 이용하자!
fun createPerson(name: String, age: Int, height: Int, weight: Int) {
    println("$name $age $height $weight")
}

createPerson("Jake", 12, 152, 43)  // IDE 도움이 없다면 파라미터가 의미하는 바를 알 수 없다.

createPerson(name = "Jake", age = 12, height = 152, weight = 43) // Good!!

createPerson(name = "Jake", 12, weight = 43, height = 152) // Good!!