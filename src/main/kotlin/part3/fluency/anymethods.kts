import kotlin.system.measureNanoTime
import kotlin.text.StringBuilder

val format = "%-10s%-10s%-10s%-10s"
val str = "context"
val result = "RESULT"
fun toString() = "lexical"
println(String.format("%-10s%-10s%-10s%-10s", "Method", "Argument", "Receiver", "Return", "result"))
println("==========================================================")


val result1 = str.let { arg ->
    print(String.format(format, "let", arg, this, result))
    result
}
println(String.format("%-10s", result1))
val result2 = str.also { arg ->
    print(String.format(format, "also", arg, this, result))
    result
}
println(String.format("%-10s", result2))
val result3 = str.run {
    print(String.format(format, "run", "N/A", this, result))
    result
}
println(String.format("%-10s", result3))
val result4 = str.apply {
    print(String.format(format, "apply", "N/A", this, result))
    result
}
println(String.format("%-10s", result4))

/*
Method    Argument  Receiver  Return
==========================================================
let       context   lexical   RESULT    RESULT
also      context   lexical   RESULT    context
run       N/A       context   RESULT    RESULT
apply     N/A       context   RESULT    context
 */

/*
- 4개의 메소드 모두 전달받은 람다를 실행
- let()과 run()은 람다를 실행시키고 람다의 결과를 호출한 곳으로 리턴
- also()와 apply()는 람다의 결과를 무시하고 컨텍스트 객체를 호출한 곳으로 리턴
- run()과 apply()는 run()과 apply()를 호출한 컨텍스트 객체의 실행 컨텍스트를 this로 사용하여 실행

뭔말이여!!!!!!!!!!
 */

// 장황하고 지저분턴 코드부터..
class Mailer {
    val details = StringBuilder()
    fun from(addr: String) = details.append("from $addr...\n")
    fun to(addr: String) = details.append("to $addr...\n")
    fun subject(line: String) = details.append("subject $line...\n")
    fun body(message: String) = details.append("body $message...\n")
    fun send() = "...sending...\n$details"
}

val mailer = Mailer()
mailer.from("builder@naver.com")
mailer.to("kong@naver.com")
mailer.subject("Your code sucks")
mailer.body("...details...")
val mailResult = mailer.send()
println(mailResult)

/*
...sending...
from builder@naver.com...
to kong@naver.com...
subject Your code sucks...
body ...details......
 */

// apply
val applyMailer =
    Mailer()
        .apply { from("builder@naver.com") }
        .apply { to("kong@naver.com") }
        .apply { subject("Your code sucks") }
        .apply { body("...details...") }

var applyMailerResult = applyMailer.send()
println(applyMailerResult)

val apply2Mailer =
    Mailer().apply {
        from("builder@naver.com")
        to("kong@naver.com")
        subject("Your code sucks")
        body("...details...")
    }
var apply2MailerResult = apply2Mailer.send()
println(apply2MailerResult)

// run
val runMailerResult =
    Mailer().run {
        from("builder@naver.com")
        to("kong@naver.com")
        subject("Your code sucks")
        body("...details...")
        send()
    }
println(runMailerResult)

/*
 apply : 연속적으로 메소드 호출을 하고 마지막에 타깃 객체를 유지
 run : 마지막에 타깃 객체가 아닌 람다 표현식의 결과를 유지
 */

// let
fun createMailer() = Mailer()
fun prepareAndSend(mailer: Mailer) = mailer.run {
    from("builder@naver.com")
    to("kong@naver.com")
    subject("Your code sucks")
    body("...details...")
    send()
}

val newMailer = createMailer()
val newResult = prepareAndSend(newMailer)
println(newResult)

println(prepareAndSend(createMailer()))
// -------
// 괄호도 많고 뭔가 지저분하다.

// let으로 개선1
val letResult = createMailer().let { mailer ->
    prepareAndSend(mailer)
}
// let은 createMailer의 결과물이다.

// let으로 개선2
createMailer().let { prepareAndSend(it) }

// let으로 개선3
createMailer().let(::prepareAndSend)

// also... void 함수 체이닝
fun prepareMailer(mailer: Mailer): Unit {
    mailer.run {
        from("builder@naver.com")
        to("kong@naver.com")
        subject("Your code sucks")
        body("...details...")
    }
}

fun sendMail(mailer: Mailer): Unit {
    mailer.send()
    println("Mail sent.....")
}

// 본인이 이런코드 정말 많이 짬..
val new2Mailer = createMailer()
prepareMailer(new2Mailer)
sendMail(new2Mailer)

// also 개선
createMailer()
    .also(::prepareMailer)
    .also(::sendMail)