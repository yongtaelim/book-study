import org.kotlinlang.play.part3.dsl.DateUtil.Tense.*
import org.kotlinlang.play.part3.dsl.days

println(2 days ago)
println(3 days from_now)

/*
kotlinc DateUtil.kt -d datedsl.jar
kotlinc -classpath datedsl.jar -script useDatedsl.kts
 */