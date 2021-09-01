package com.jessyt.`object`.chapter2.sub7

/**
 * Created by LYT to 2021/08/31
 */
class WebPage(
    private var content: String
) {
    fun content(): String =
        this.content

    fun update(content: String) {
        this.content = content
    }
}