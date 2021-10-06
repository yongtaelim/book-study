package com.jessyt.`object`.chapter4.sub1

/**
 * Created by LYT to 2021/10/02
 */
class Page(
    val title: String?
) {
    fun badTitle(): String? {
        if (title == null) {
            return null
        }
        return "Elegant Objects"
    }

    fun title(): String {
        if (title == null) {
            throw IllegalArgumentException("타이틀이 존재하지 않습니다.")
        }
        return "Elegant Objects"
    }
}

fun use() {
    val page = Page(null)
    val title = page.title
    if (title == null) {
        println("Can't print. it's not a tile.")
        return
    }
    println("title length=${title.length}")
}

