package com.jessyt.`object`.chapter4.sub3

/**
 * Created by LYT to 2021/10/12
 */
open class Document1 {
    fun length(): Int {
        return content().length
    }

    open fun content(): String {
        // read document
        // load byte array
        return ""
    }
}

class EncryptedDocument1: Document1() {

    override fun content(): String {
        // read document
        // load byte array
        // encrypt document
        return ""
    }
}

fun test() {
    val length = EncryptedDocument1().length()
}

interface Document2 {
    fun length(): Int
    fun content(): String
}

class DefaultDocument2: Document2 {
    override fun length(): Int {
        // code 동일
    }

    override fun content(): String {
        // code 동일
    }

}

class EncryptedDocument2(
    private val plain: Document2
): Document2 {
    override fun length(): Int {
        return this.plain.length()
    }

    override fun content(): String {
        val content = this.plain.content()
        return encrypt(content)
    }

}

abstract class Document {
    abstract fun content(): String
    fun length(): Int {
        return this.content().length
    }
}

class DefaultDocument: Document() {
    override fun content(): String {
        // read document
    }
}

class EncryptedDocument: Document() {
    override fun content(): String {
        // read document
        // encrypt document
    }

}