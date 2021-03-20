import java.util.Arrays

// 타입 불변성
// List<T>로 예를 들어본다.
// 먼저 2개의 클래스가 있고 Dog extends Animal이 있다.
// List<Animal>을 변수로 받는다고 해서 List<Dog>는 불가하다.

open class Fruit

class Banana : Fruit()
class Orange : Fruit()

fun receiveFruitsMutable(fruits: Array<Fruit>) {
    println("Number of fruits: ${fruits.size}")
}

val bananas: Array<Banana> = arrayOf()
//receiveFruitsMutable(bananas) // ERROR type이 맞지않다.

fun receiveFruitImmutable(fruits: List<Fruit>) {
    println("Number of fruits: ${fruits.size}")
}

//receiveFruitImmutable(bananas) // ERROR type이 맞지않다.
// 공변성
// 파라미터의 값을 읽기만 하기 때문에 Fruit 하위 클래스가 전달되더라도 문제가 없다.
// 이런 것을 타입이나 파생 타입에 접근하기 위한 파라미터 타입의 공변성이라고 한다.
// 공변성을 사용하면 컴파일러에게 자식 클래스를 부모 클래스의 자리에 사용할 수 있게 요청한다.
fun receiveFruit(fruits: Array<out Fruit>) {
    println("Number of fruits: ${fruits.size}")
}

receiveFruit(bananas)  // 가능하다.

// 반공변성
// 상위 클래스의 변수가 전달되도 문제가 없다.
// 이것을 반공변성이라고 한다.
// 반공변선을 사용하면 받을 수만 있고 다른곳으로 리턴은 불가하다.
fun receiveFruit(fruit: Array<in Fruit>) {
    println("Number of fruits: ${fruit.size}")
}

val anythings = Array<Any>(3) { _ -> Fruit() }
receiveFruit(anythings)