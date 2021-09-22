package com.jessyt.`object`.chapter3.sub4

import java.net.URI
import java.util.*

/**
 * Created by LYT to 2021/09/17
 */
class WebPage1(
    private val uri: URI
) {
    fun content(): String {
        // 외부 연동하여 웹페이지의 Content을 읽어 반환
        return ""
    }
}

class WebPage(
    var uri: URI
) {
    fun content(): String {
        // 외부 연동하여 웹페이지의 Content을 읽어 반환
        return ""
    }
}

fun test2() {
    val webPage = WebPage(URI("https://www.naver.com"))
    webPage.uri = URI("https://www.google.com")
}

class ImmutableList<T> (
    private val items: MutableList<T>
) {
    fun add(number: T) {
        items.add(number)
    }

    fun iterate() : Iterable<T> =
        Collections.unmodifiableList(items)
}

fun test4() {
    val list = ImmutableList<Int>(mutableListOf())
    list.add(1)
    list.add(2)
}

enum class State(
    val desc: String
) {
    READY("준비"),
    START("시작"),
    FINISH("종료");

    companion object {
        fun ready(): State = READY
    }
}

fun test() {
    val ready = State.ready()

}