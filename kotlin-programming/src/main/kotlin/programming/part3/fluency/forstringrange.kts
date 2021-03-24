import kotlin.collections.Iterator

operator fun ClosedRange<String>.iterator() =
    object : Iterator<String> {
        private val next = StringBuilder(start)
        private val last = endInclusive
        override fun hasNext() = last >= next.toString() && last.length >= next.length
        override fun next(): String {
            val result = next.toString()
            val lastCharacter = next.last()
            if (lastCharacter < Char.MAX_VALUE) {
                next.setCharAt(next.length - 1, lastCharacter + 1)
            } else {
                next.append(Char.MIN_VALUE)
            }
            return result
        }
    }

for (word in "hell".."help") {
    println("$word")
}

/*
 Static 메소드 인젝팅
 클래스의 컴패니언 객체를 확장해서 static 메소드를 인젝팅할 수 있다.
 메소드를 클래스가 아니라 컴패니언에 인젝팅이 가능하다.
 */
fun String.Companion.toURL(link: String) = java.net.URL(link)
val url: java.net.URL = String.toURL("https://naver.com")