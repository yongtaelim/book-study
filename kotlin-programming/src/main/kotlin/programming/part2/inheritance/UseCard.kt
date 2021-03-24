import org.kotlinlang.play.part2.inheritance.*

/**
 * sealed 클래스의 when 표현식을 사용할 때 else를 사용하면 안된다.
 * when에 자식 클래스가 속할 수 있는 조건이 누락된 경우 컴파일러가 else 조건을 추가하라는 제안을 하게 된다.
 * 하지만 sealed when을 사용할 때는 else를 추가하지 말자!
 */

fun process(card: Card) = when (card) {
    is Ace -> "${card.javaClass.name} of ${card.suit}"
    is King, is Queen, is Jack -> "$card"
    is Pip -> "${card.number} of ${card.suit}"
}

fun main() {
//    println(process(Ace("Diamond")))
//    println(process(Queen("Clubs")))
//    println(process(Pip("Spades", 2)))
//    println(process(Pip("Hearts", 6)))
}