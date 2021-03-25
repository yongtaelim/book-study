package me.pratice.kotlinmicroservice.chapter5.controller

import me.pratice.kotlinmicroservice.chapter5.domain.Store
import me.pratice.kotlinmicroservice.chapter5.service.StoreService
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.mockito.BDDMockito.*
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import org.springframework.test.web.servlet.result.MockMvcResultHandlers.print
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status

@SpringBootTest
@AutoConfigureMockMvc
internal class StoreController2Test {

    @Autowired
    lateinit var mockMvc: MockMvc

    @MockBean
    lateinit var storeService: StoreService

    @Test
    @DisplayName("store id로 store를 조회")
    internal fun getStoreTest() {


        given(storeService.getStore(1))
            .willReturn(Store(id = 1, name = "스토어1", address = "주소1"))

        mockMvc.perform(get("/stores/{id}", 1))
            .andExpect(status().isOk)
            .andExpect(jsonPath("\$.id").value(1))
            .andExpect(jsonPath("\$.name").value("스토어1"))
            .andExpect(jsonPath("\$.address").value("주소1"))
            .andDo(print())

        reset(storeService)
    }

    @Test
    @DisplayName("store 리스트를 조회")
    fun getAllStoreTest() {
        given(storeService.getAllStores())
            .willReturn(listOf(
                Store(id = 1, name = "스토어1", address = "주소1"),
                Store(id = 2, name = "스토어2", address = "주소2"),
                Store(id = 3, name = "스토어3", address = "주소3"),
            ))

        mockMvc.perform(get("/stores"))
            .andExpect(status().isOk)
            .andExpect(jsonPath("\$[0].id").value(1))
            .andExpect(jsonPath("\$[0].name").value("스토어1"))
            .andExpect(jsonPath("\$[0].address").value("주소1"))
            .andExpect(jsonPath("\$[1].id").value(2))
            .andExpect(jsonPath("\$[1].name").value("스토어2"))
            .andExpect(jsonPath("\$[1].address").value("주소2"))
            .andExpect(jsonPath("\$[2].id").value(3))
            .andExpect(jsonPath("\$[2].name").value("스토어3"))
            .andExpect(jsonPath("\$[2].address").value("주소3"))
            .andDo(print())

        then(storeService).should(times(1)).getAllStores()      // 1번 call?
        then(storeService).shouldHaveNoMoreInteractions()  // 그 후에 서비스와 더 이상 어떤 상호작용도 없었니?

        reset(storeService)
    }
}