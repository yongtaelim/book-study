object Sun : Runnable {
    val radiusInKM = 615453
    var coreTemperatureInC = 1908527
    override fun run() {
        println("spin....")
    }
}

fun moveIt(runnable: Runnable) {
    runnable.run()
}

println(Sun.radiusInKM)
moveIt(Sun)