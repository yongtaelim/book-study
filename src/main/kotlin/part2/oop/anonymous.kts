// 익명 내부 객체는 인터페이스를 구현하는데 사용
// object 키워드와 블록 사이에 콜론을 사용한다.
fun createRunnable1(): Runnable {
    var runnable = object: Runnable {
        override fun run() {
            println("runnable run..")
        }
    }
    return runnable
}

val runnable = createRunnable1()
runnable.run()

// 싱글 추상 메서드 인터페이스라면 아래와 같이 사용할 수도 있다.
fun createRunnable(): Runnable = Runnable { println("runnalbe run..") }
createRunnable().run()

fun createRunnable2() : Runnable = object: Runnable, AutoCloseable {
    override fun run() {
        println("run..")
    }

    override fun close() {
        println("close..")
    }

}