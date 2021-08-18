package com.jessyt.`object`.chapter2.sub4

import java.awt.Color

/**
 * Created by LYT to 2021/08/17
 */
class Pixel(
    val x: Int, val y: Int
) {
    fun paint(color: Color) {

    }
}

class UseTest {
    fun use() {
        val center = Pixel(50, 50)

        val red = 2598732
        center.paint(Color(red))
    }
}

//class Book {
//    fun withAuthor(author: String): Book
//    fun withTitle(title: String): Book
//    fun withPage(page: Page): Book
//}