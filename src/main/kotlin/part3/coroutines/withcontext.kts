import kotlinx.coroutines.*

suspend fun task1() {
    println("start task1 in Thread ${Thread.currentThread()}")
    yield()
    println("end task1 in Thread ${Thread.currentThread()}")
}

suspend fun task2() {
    println("start task2 in Thread ${Thread.currentThread()}")
    yield()
    println("end task2 in Thread ${Thread.currentThread()}")
}

runBlocking {
    println("starting in Thread ${Thread.currentThread()}")
    withContext(Dispatchers.Default) { task1() }
    launch { task2() }
    println("ending in Thread ${Thread.currentThread()}")
}

/*
kotlinc-jvm -classpath /Users/limyongtae/.gradle/caches/modules-2/files-2.1/org.jetbrains.kotlinx/kotlinx-coroutines-core/1.2.2/6ff48bdfc38a8c22e3fc37605b6a6afaed3b6dbd/kotlinx-coroutines-core-1.2.2.jar
 */