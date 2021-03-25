package me.pratice.kotlinmicroservice.chapter3.handler

//import javax.servlet.http.HttpServletRequest

//@ControllerAdvice
//class ErrorHandler {
//    @ExceptionHandler(JsonParseException::class)
//    fun jsonParseExceptionHandler(servletRequest: HttpServletRequest, exception: Exception) : ResponseEntity<ErrorResponse> =
//        ResponseEntity(ErrorResponse("JSON Error", exception.message ?: "invalid json"), HttpStatus.BAD_REQUEST)
//}