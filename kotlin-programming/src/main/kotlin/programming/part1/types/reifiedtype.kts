import java.lang.RuntimeException

abstract class Book(val name: String)

class Fiction(name: String) : Book(name)
class NonFiction(name: String) : Book(name)

val books: List<Book> = listOf(Fiction("Moby Dick"), NonFiction("Learn to Code"), Fiction("LOTR"))

// 변수에 Class를 넘겨하는 불편함이 있다.
fun <T> findFirst(books: List<Book>, ofClass: Class<T>): T {
    val selected = books.filter { book -> ofClass.isInstance(book) }
    if (selected.isEmpty()) {
        throw RuntimeException("Not found");
    }
    return ofClass.cast(selected[0])
}

println(findFirst(books, NonFiction::class.java).name)

// reified : 구체화
// inline으로 선언하면 reified는 inline 선언한 메서드 내에서만 사용가능
// 함수의 바디가 함수 호출하는 부분에서 확장되기 때문에 타입 T는 컴파일 시간에 확인되는 실제 타입으로 대체된다.
inline fun <reified T> findFirst(books: List<Book>): T {
    val seleted = books.filter { book -> book is T }
    if (books.isEmpty()) {
        throw RuntimeException("Not found")
    }
    return seleted[0] as T
}


// reified 타입 파라미터를 사용하는 함수를 주시!!
//listOf<T>(), mutableListOf<T>(), parse<T>가 있다.