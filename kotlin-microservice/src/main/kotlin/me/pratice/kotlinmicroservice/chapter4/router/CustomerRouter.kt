package me.pratice.kotlinmicroservice.chapter4.router

import me.pratice.kotlinmicroservice.chapter4.handler.CustomerHandler
import me.pratice.kotlinmicroservice.common.domain.Customer
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Bean
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.server.RouterFunction
import org.springframework.web.reactive.function.server.ServerRequest
import org.springframework.web.reactive.function.server.router
import reactor.kotlin.core.publisher.toMono

/**
 * 이 경우 빈이 노출되면 컴포넌트 스캔을 통해 새로운 방식의 RouterFunction을 만들고 웹 애플리케이션의 경로를 정의할 수 있다.
 * 아래와 같은 경우 라우터가 /functional 경로의 모든 요청을 처리한다.
 */
@Component
class CustomerRouter(private val customerHandler: CustomerHandler) {

    @Bean
    fun customerRoutes(): RouterFunction<*> = router {
        "/functional".nest {
            "/customer".nest {
                GET("/{id}", customerHandler::get)
                POST("", customerHandler::create)
            }
            "/customers".nest {
                GET("", customerHandler::search)
            }
        }
    }
}