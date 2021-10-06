package com.jessyt.`object`.chapter4.sub2

import java.io.File
import java.io.FileInputStream

/**
 * Created by LYT to 2021/10/05
 */
class Page {
    fun length1(file: File): Int {
        if (!file.exists())
            throw IllegalArgumentException("file doesn't exist")
        return content1(file).length
    }

    fun content1(file: File): String {
        return "file content"
    }

    fun use() {
        val file = File("/path/file")
        length1(file)
    }

    fun length(file: File): Int {
        return content(file).size
    }

    fun content(file: File): ByteArray {
        return FileInputStream(file).readAllBytes()
    }
}

