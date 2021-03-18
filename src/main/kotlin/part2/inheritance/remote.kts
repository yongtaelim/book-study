// interface 작성 방법
interface Remote {
    fun up()
    fun down()
    fun doubleUp() {
        up()
        up()
    }
    companion object {
        fun combine(first: Remote, second: Remote): Remote = object: Remote {
            override fun up() {
                first.up()
                second.up()
            }

            override fun down() {
                first.down()
                second.down()
            }

        }
    }
}

class TV {
    var volume = 0
}

// interface 구현 방법
class TVRemote(val tv: TV): Remote {
    override fun up() {
        tv.volume++
    }

    override fun down() {
        tv.volume--
    }

}

val tv = TV()
val tvRemote: TVRemote = TVRemote(tv)
println("Volume: ${tv.volume}")
tvRemote.up()
println("Volume: ${tv.volume}")
tvRemote.doubleUp()
println("Volume: ${tv.volume}")

/**
 * Java에서는 interface에 static 메서드 구현가능하나 kotlin에서는 불가
 * interface에 static 메서드를 작성하기 위해서는 컴패니언 객체를 사용
 */
val anotherTV = TV()
Remote.combine(tvRemote, TVRemote(anotherTV))

