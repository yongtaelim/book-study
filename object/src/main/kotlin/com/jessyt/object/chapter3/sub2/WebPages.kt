package com.jessyt.`object`.chapter3.sub2

/**
 * Created by LYT to 2021/09/14
 */
class WebPages {
    companion object {
        fun read(url: String): String {
            // HTTP 요청 생성
            // UTF-8 문자열로 변환
            return ""
        }
    }
}

class Test {
    fun use() {
        val html1 = WebPages.read("http://www.java.com")
        val html = WebPage("http://www.java.com").content()
    }
}

class WebPage(
    private val url: String
) {
    fun content(): String {
        // HTTP 요청 생성
        // UTF-8 문자열로 변환
        return ""
    }
}

