import kotlin.reflect.KProperty

// List<Map>에서 특정 Key값에 해당하는 value값을 내가 원하는 형태로 convert한다.
class PoliteString(private val dataSource: MutableMap<String, Any>) {
    operator fun getValue(thisRef: Any?, property: KProperty<*>) =
        (dataSource[property.name] as? String)?.replace("stupid", "s*****") ?: ""

    operator fun setValue(thisRef: Any, property: KProperty<*>, value: String) {
        dataSource[property.name] = value
    }
}

class TestString(private var content: String) {
    operator fun getValue(thisRef: Any?, property: KProperty<*>) =
        content.replace("su", "**")

    operator fun setValue(thisRef: Any, property: KProperty<*>, value: String) {
        content = value
    }
}

class TTT {
    val text: String by TestString("su")
}

class PostComment(dataSource: MutableMap<String, Any>) {
    val title: String by dataSource
    var likes: Int by dataSource

    val comment: String by PoliteString(dataSource)
    override fun toString() = "Title : $title Likes: $likes Comment: $comment"
}

val data = listOf(
    mutableMapOf<String, Any>(
        "title" to "Using Delegation",
        "likes" to 2,
        "comment" to "Keep it simple, stupid"
    ),
    mutableMapOf<String, Any>(
        "title" to "Using Ingeritance",
        "likes" to 1,
        "comment" to "Prefer Delegation where possible"
    )
)

val forPost1 = PostComment(data[0])
val forPost2 = PostComment(data[1])

forPost1.likes++
println(forPost1)
println(forPost2)