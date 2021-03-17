val name = "Eve"

val memo = """Dear $name, a quick reminder about the
party we gave scheduled next Tue
'Low Ceredf | Please....
"""

println(memo)

// 이스케이프 바이바이 \" \"
// RAW 문자열 """ """

fun createMemoFor(name: String): String {
    if (name == "eee") {
        val memo = """asdfasfasdfasdfasdf
        asdfasdfasdf
        sadfasdfasdfasdfasdf"""
        return memo
    }
    return ""
}

println(createMemoFor("eee"))

fun createMemoFor2(name: String): String {
    if (name == "eee") {
        val memo = """asdfasfasdfasdfasdf
        | asdfasdfasdf
        | sadfasdfasdfasdfasdf"""
        return memo.trimMargin()
    }
    return ""
}

println(createMemoFor2("eee"))