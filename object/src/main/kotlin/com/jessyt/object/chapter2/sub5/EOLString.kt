package com.jessyt.`object`.chapter2.sub5

import java.lang.IllegalStateException

/**
 * Created by LYT to 2021/08/21
 */
class EOLString(
    private val origin: String
) {
    override fun toString(): String {
        /* EOL을 사용할 수 없는 경우 */
        if ( origin.length > 10 )
            throw IllegalStateException("EOL을 사용할 수 없습니다.")

        return String.format("%s\r\n", this.origin)
    }
}

