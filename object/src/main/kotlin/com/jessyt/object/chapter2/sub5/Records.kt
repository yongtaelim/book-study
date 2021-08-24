package com.jessyt.`object`.chapter2.sub5

import java.io.Writer

/**
 * Created by LYT to 2021/08/21
 */
class Records(
    private val records: MutableList<Record>
) {
    companion object {
        private const val EOL = "\r\n"
    }

    fun all() = records

    fun write(out: Writer) {
        for (record in this.all()) {
            out.write("${EOLString(record.toString())}")
        }
    }
}

class Record(
    val rec: String
)