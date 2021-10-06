package com.jessyt.`object`.chapter4.sub1

/**
 * Created by LYT to 2021/10/05
 */
interface UserRepository {
    fun findByName(name: String): User?
}