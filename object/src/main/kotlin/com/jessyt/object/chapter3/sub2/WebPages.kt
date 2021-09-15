//package com.jessyt.`object`.chapter3.sub2
//
//import java.io.File
//import java.util.*
//import java.util.function.Predicate
//import javax.sql.rowset.FilteredRowSet
//
///**
// * Created by LYT to 2021/09/14
// */
//class WebPages {
//    companion object {
//        fun read(url: String): String {
//            // HTTP 요청 생성
//            // UTF-8 문자열로 변환
//            return ""
//        }
//    }
//}
//
//class Test {
//    fun use() {
//        val html1 = WebPages.read("http://www.java.com")
//        val html = WebPage("http://www.java.com").content()
//    }
//}
//
//class WebPage(
//    private val url: String
//) {
//    fun content(): String {
//        // HTTP 요청 생성
//        // UTF-8 문자열로 변환
//        return ""
//    }
//}
//
//class YongMath {
//    companion object {
//        fun between(l: Int, r: Int, x: Int) =
//            Math.min(Math.max(l, x), r)
//
//        fun between(num: Number) =
//            // 새로운 기능
//    }
//}
//
//class Test3 {
//    fun use() {
//        val y = YongMath.between(5,9, 13) // 9
//        if ( /* y가 필요한가? */ )
//            println("y=$y")
//
//        val yy = Between(5, 9, 13)
//        if ( /* y가 필요한가? */ )
//            println("y=$y")
//    }
//}
//
//class Between(
//    private val num: Number
//): Number() {
//    constructor(l: Int, r: Int, x: Int): this(Min(Max(l, x), r))
//
//    constructor(num: Number): this(num)
//
//    override fun toInt(): Int =
//        this.num.toInt()
//}
//
//class Test4 {
//    fun test1() {
//        val numbers = listOf(1,2,3,4,5,6,7,8,9,10)
//
//        val evens = mutableListOf<Number>()
//        for (number in numbers) {
//            if (number % 2 == 0) {
//                evens.add(number)
//            }
//        }
//    }
//
//    fun test2() {
//        val numbers = listOf(1,2,3,4,5,6,7,8,9,10)
//
//        val evens = Filtered(
//            numbers,
//            Predicate<Int>() {
//                override fun suitable(number: Int): Boolean =
//                    number % 2 == 0
//            }
//        )
//    }
//
//}
//
//class FileLines(
//    private val file: File
//): Iterable<String> {
//    override fun iterator(): Iterator<String> =
//        listOf(FileUtils.readLines(this.file)).iterator()
//}

class User {
    lateinit var name: String

    companion object {
        private val INSTANCE = User()
        fun getInstance() = INSTANCE
    }
}

class Test6 {
    fun test() {
        val a = User.getInstance()
        a.name = ""
    }
}