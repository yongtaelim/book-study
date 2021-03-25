package me.pratice.kotlinmicroservice.chapter5.service

import org.amshove.kluent.*
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNotNull
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.junit.jupiter.SpringExtension

@ExtendWith(SpringExtension::class)
@SpringBootTest
internal class StoreServiceTest {
    @Autowired
    lateinit var storeService: StoreService

    @Test
    @DisplayName("id를 이용하여 Store 객체를 조회")
    fun getStoreTest() {
        val store = storeService.getStore(1)
        assertNotNull(store )
        store.shouldNotBeNull()
        store.`should not be null`()
        store.id `should be` 1  // infix 함수 이용
    }

    @Test
    @DisplayName("모든 Store 객체 리스트를 조회")
    fun getAllStoresTest() {
        val allStores = storeService.getAllStores()
        assertEquals(allStores.size, 3)
        allStores.size `should be equal to` 3
        allStores.size `should be greater than` 0
        allStores.size `should be less or equal to` 3
        allStores.size `should be in range` 1..3
    }
}