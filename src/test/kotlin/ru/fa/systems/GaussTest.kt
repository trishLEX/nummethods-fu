package ru.fa.systems

import org.junit.jupiter.api.Test

class GaussTest {

    @Test
    fun test() {
        val m = Matrix(
            arrayOf(
                Vector(arrayOf(2.0, -1.0, 5.0)),
                Vector(arrayOf(1.0, 1.0, -3.0)),
                Vector(arrayOf(2.0, 4.0, 1.0))
            )
        )
        val b = Vector(arrayOf(10.0, -2.0, 1.0))
        println(Gauss(m, b).evaluate())
    }
}