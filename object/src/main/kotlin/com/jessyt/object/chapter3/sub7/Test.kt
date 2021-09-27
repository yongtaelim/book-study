package com.jessyt.`object`.chapter3.sub7

/**
 * Created by LYT to 2021/09/26
 */
class Test {
//    fun <T> size(items: Iterable<T>): Int {
//        if (items is Collection) {
//            return items.size
//        }
//        var size = 0
//        for (item in items) {
//            ++size
//        }
//        return size
//    }

    fun <T> size(items: Iterable<T>): Int {
        var size = 0
        for (item in items) {
            ++size
        }
        return size
    }

    fun <T> size(items: Collection<T>): Int = items.size

    fun <T> test(items: Iterable<T>): Int {
        if (items is Collection) {
            return items.size
        }
    }
}