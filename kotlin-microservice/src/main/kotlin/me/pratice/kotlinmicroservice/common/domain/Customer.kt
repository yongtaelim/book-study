package me.pratice.kotlinmicroservice.common.domain

import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.annotation.JsonInclude.*

/**
 * 아래 설정으로 null값은 직렬화하지 않을 수 있다. or application.yml로도 설정가능
 * spring.jackson.default-property-inclusion: NON_NULL
 */

@JsonInclude(Include.NON_NULL)
data class Customer(
    var id: Int = 0,
    var name: String = "",
    val telephone: Telephone? = null
)
