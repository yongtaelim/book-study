package me.pratice.kotlinmicroservice.chapter5.service

import me.pratice.kotlinmicroservice.chapter5.domain.Store
import org.springframework.stereotype.Service
import java.util.concurrent.ConcurrentHashMap

@Service
class StoreServiceImpl: StoreService {
    companion object {
        private val initStore = arrayOf(
            Store(id = 1, name = "스토어1", address = "주소1"),
            Store(id = 2, name = "스토어2", address = "주소2"),
            Store(id = 3, name = "스토어3", address = "주소3"),
        )
    }

    private val stores = ConcurrentHashMap<Int, Store>(initStore.associateBy(Store::id))

    override fun getStore(id: Int) = stores[id]
    override fun getAllStores() = stores.map(Map.Entry<Int, Store>::value).toList()
}