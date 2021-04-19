package org.kotlinlang.play.part2.inheritance

import java.lang.RuntimeException

// 씰드 클래스
/**
 * 코틀린의 한쪽 극단에 final 클래스가 존재
 * final 클래스란 open으로 표기되어 있지 않아서 자식클래스가 하나도 없는 클래스
 * 그와 상반되게 open과 abstract 클래스에서 어떤 클래스가 상속을 받았는지 전혀 알수 없다.
 *
 * 클래스를 만들 때 개발자가 지정한 클래스에만 상속할 수 있도록 하는 클래스
 */

sealed class Card(val suit: String)

class Ace(suit: String) : Card(suit)
class King(suit: String) : Card(suit) {
    override fun toString() = "King of $suit"
}
class Queen(suit: String) : Card(suit) {
    override fun toString() = "Queen of $suit"
}
class Jack(suit: String) : Card(suit) {
    override fun toString() = "Jack of $suit"
}
class Pip(suit: String, val number: Int) : Card(suit) {
    init {
        if (number < 2 || number > 10) {
            throw RuntimeException("error")
        }
    }
}



//
sealed class Card(val suit: Suit)

class Ace(suit: Suit) : Card(suit)
class King(suit: Suit) : Card(suit) {
    override fun toString() = "King of $suit"
}
class Queen(suit: Suit) : Card(suit) {
    override fun toString() = "Queen of $suit"
}
class Jack(suit: Suit) : Card(suit) {
    override fun toString() = "Jack of $suit"
}
class Pip(suit: Suit, val number: Int) : Card(suit) {
    init {
        if (number < 2 || number > 10) {
            throw RuntimeException("error")
        }
    }
}


