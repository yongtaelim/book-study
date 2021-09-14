package com.jessyt.`object`.chapter2.sub7

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

/**
 * Created by LYT to 2021/08/31
 */
internal class WebPagesTest {

    @Test
    fun `check content`() {
        // Given
        val content = "꾸준히 성장하자."

        // When
        val webPage = WebPage(content)

        // Then
        assertThat(webPage.content()).isEqualTo(content)
    }

    @Test
    fun `update content`() {
        // Given
        val content = "꾸준히 성장하자."
        val webPage = WebPage(content)

        val updateContent = "천천히 꾸준히 성장하자."
        // When
        webPage.update(updateContent)

        // Then
        assertThat(webPage.content()).isEqualTo(updateContent)
    }
}