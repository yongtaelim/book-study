package com.jessyt.`object`.chapter4.sub1

import java.util.*

/**
 * Created by LYT to 2021/10/05
 */
class Users(
    private val repository: UserRepository
) {
    fun user(name: String): Users? {
        val user = repository.findByName(name)
        if (user == null)
            return null
        return user
    }

    fun getUsers(name: String): List<Users> {
        val user = repository.findByName(name)
        if (user == null)
            return Collections.emptyList()

        return Collections.singletonList(user)
    }

    fun test() {
        val users = getUsers("")
        if (users.isEmpty()) {
            val user = users[0]
            // user 사용
        }
    }
}

interface User {
    fun name(): String
    fun raise(salary: String)
}

class NullUser(
    private val label: String
): User {
    override fun name(): String {
        return this.label
    }

    override fun raise(salary: String) {
        throw IllegalStateException("봉급을 인상할 수 없습니다. 나는 Stub입니다!")
    }

}