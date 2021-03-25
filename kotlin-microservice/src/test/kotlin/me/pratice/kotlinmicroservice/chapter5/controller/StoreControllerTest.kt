package me.pratice.kotlinmicroservice.chapter5.controller

import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.junit.jupiter.MockitoExtension
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.TestConstructor
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import org.springframework.test.web.servlet.result.MockMvcResultHandlers.print
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status

@SpringBootTest
@AutoConfigureMockMvc
@TestConstructor(autowireMode = TestConstructor.AutowireMode.ALL)
internal class StoreControllerTest(val mockMvc: MockMvc) {

    @Test
    @DisplayName("store id로 store를 조회")
    internal fun getStoreTest() {
        mockMvc.perform(get("/stores/{id}", 1))
            .andExpect(status().isOk)
            .andExpect(jsonPath("\$.id").value(1))
            .andExpect(jsonPath("\$.name").value("스토어1"))
            .andExpect(jsonPath("\$.address").value("주소1"))
            .andDo(print())

    }

    @Test
    @DisplayName("store 리스트를 조회")
    fun getAllStoreTest() {
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
    }

//    Store(id = 1, name = "스토어1", address = "주소1"),
//    Store(id = 2, name = "스토어2", address = "주소2"),
//    Store(id = 3, name = "스토어3", address = "주소3"),

}