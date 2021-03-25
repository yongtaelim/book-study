package me.pratice.kotlinmicroservice.chapter5.service

import me.pratice.kotlinmicroservice.chapter5.domain.Store

interface StoreService {
    fun getStore(id: Int): Store?
    fun getAllStores(): List<Store>
}