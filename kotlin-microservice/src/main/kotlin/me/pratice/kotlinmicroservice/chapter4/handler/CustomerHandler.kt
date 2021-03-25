package me.pratice.kotlinmicroservice.chapter4.handler

import me.pratice.kotlinmicroservice.chapter4.service.CustomerService
import me.pratice.kotlinmicroservice.common.domain.Customer
import me.pratice.kotlinmicroservice.common.domain.ErrorResponse
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.BodyInserters.fromValue
import org.springframework.web.reactive.function.server.ServerRequest
import org.springframework.web.reactive.function.server.ServerResponse.*
import org.springframework.web.reactive.function.server.bodyToMono
import java.net.URI

@Component
class CustomerHandler(val customerService: CustomerService) {

    /**
     * pathVariable 사용방법
     * responseEntity 사용방법
     * /funtional/customer/1
     *
     * import org.springframework.web.reactive.function.server.ServerResponse
     *   - notFount, ok, badRequest, deleted, created, status
     */
    fun get(serverRequest: ServerRequest) =
        customerService.getCustom(serverRequest.pathVariable("id").toInt())
            .flatMap { ok().body(fromValue(it)) }
//            .switchIfEmpty(notFound().build())
            .switchIfEmpty(status(HttpStatus.NOT_FOUND).build())

    /**
     * queryParam 사용방법
     * /functional/customers?nameFilter=in
     */
    fun search(serverRequest: ServerRequest) =
        ok().body(customerService.searchCustomers(serverRequest.queryParam("nameFilter").orElse("")),
            Customer::class.java)

    /**
     * JSON 본문 처리하기
     * 오류 처리
     * /funtional/customer
     *   - body { "id": 18, "name": "cookie" }
     */
    fun create(serverRequest: ServerRequest) =
        customerService.createCustomer(serverRequest.bodyToMono()).flatMap {
//            status(HttpStatus.CREATED).body(fromValue(it))                  // 객체 반환
            created(URI.create("/functional/customer/${it.id}")).build()  // header로 받기
        }.onErrorResume(Exception::class.java) {
            badRequest().body(fromValue(ErrorResponse("error creating customer", it.message ?: "error")))
        }
}