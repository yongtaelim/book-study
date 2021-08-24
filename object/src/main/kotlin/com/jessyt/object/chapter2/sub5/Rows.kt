package com.jessyt.`object`.chapter2.sub5

import java.io.PrintStream

/**
 * Created by LYT to 2021/08/21
 */
class Rows(
    private val rows: MutableList<Row>
) {
    companion object {
        private const val EOL = "\r\n"
    }

    private fun fetch() = rows

    fun print(pnt: PrintStream) {
        for (row in this.fetch()) {
            pnt.print("${EOLString("{ $row }")}")
        }
    }
}

class Row(
    val r: String
)