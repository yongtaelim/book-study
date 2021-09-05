package com.jessyt.`object`.chapter2.sub8

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import org.mockito.Mockito

/**
 * Created by LYT to 2021/09/04
 */
internal class BadCashTest {

    @Test
    fun `cash test`() {
        // Given
        val exchange = Mockito.mock(BadExchange::class.java)
        Mockito.doReturn(1.15)
            .`when`(exchange)
            .rate("USD", "EUR")

        // When
        val dollar = BadCash(exchange, 500)
        val euro = dollar.`in`("USD")

        // Then
        assertThat(euro.toString()).isEqualTo("575")

    }

}