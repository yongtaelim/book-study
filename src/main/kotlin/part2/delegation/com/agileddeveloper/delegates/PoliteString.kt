class PoliteString(var content: String) {
    operator fun getValue(thisRef: Any?, property: kProperty<*>) = content.replace("stupid", "s*****")
    operator fun setValue(thisRef: Any, property: KProperty<*>, value: String) {
        content = value
    }
    fun beingpolite(content: String) = PoliteString(content)
}