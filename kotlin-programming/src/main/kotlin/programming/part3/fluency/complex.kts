import kotlin.math.abs

data class Complex(val real: Int, val imaginary: Int) {
    // operator fun times은 *와 동일
    operator fun times(other: Complex) =
        Complex(
            real * other.real - imaginary * other.imaginary,
            real * other.imaginary + imaginary * other.real
        )

    private fun sign() = if (imaginary < 0) "-" else "+"
    override fun toString() = "$real ${sign()} ${abs(imaginary)}i"
}

println(Complex(4, 2) * Complex(-3, 4))
println(Complex(1, 2) * Complex(-3, 4))

// 연산자 오버로딩..
/*
+x          x.unaryPlus()
-x          x.unaryMinus()
!x          x.not()
x + y       x.plus(y)
x - y       x.minus(y)
x * y       x.times(y)
x / y       x.div(y)
x % y       x.rem(y)
++x         x.inc()
x++         x.inc()
--x         x.dec()
x--         x.dec()
x == y      x.equals(y)
x != y      !(x.equals(y))
x < y       x.compareTo(y)
x[i]        x.get(i)
x[i] = y    x.set(i, y)
y in x      x.contains(y)
x..y        x.rangeTo(y)
x()         x.invoke()
x(y)        x.invoke(y)
 */