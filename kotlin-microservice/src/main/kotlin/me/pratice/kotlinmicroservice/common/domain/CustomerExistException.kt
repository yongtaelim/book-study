package me.pratice.kotlinmicroservice.common.domain

class CustomerExistException(override val message: String) : Exception(message) {
}