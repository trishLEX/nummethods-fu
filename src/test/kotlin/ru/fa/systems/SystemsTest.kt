package ru.fa.systems

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import ru.fa.MethodResult

class SystemsTest {

    @Test
    fun testGauss() {
        val m = Matrix(
            arrayOf(
                Vector(arrayOf(2.0, -1.0, 5.0)),
                Vector(arrayOf(1.0, 1.0, -3.0)),
                Vector(arrayOf(2.0, 4.0, 1.0))
            )
        )
        val b = Vector(arrayOf(10.0, -2.0, 1.0))
        val res = Systems(m, b).gauss()
        Assertions.assertEquals(
            MethodResult(Vector(arrayOf(1.9999999999999996, -0.9999999999999998, 1.0000000000000002)), 3),
            res
        )
    }

    @Test
    fun testGauss2() {
        val m = Matrix(
            arrayOf(
                Vector(arrayOf(4.0, 2.0, -1.0)),
                Vector(arrayOf(5.0, 3.0, -2.0)),
                Vector(arrayOf(3.0, 2.0, -3.0))
            )
        )
        val b = Vector(arrayOf(1.0, 2.0, 0.0))
        val res = Systems(m, b).gauss()
        Assertions.assertEquals(
            MethodResult(Vector(arrayOf(-0.9999999999999986, 2.999999999999998, 1.0)), 3),
            res
        )
    }

    @Test
    fun testChangeMainRow() {
        val m = Matrix(
            arrayOf(
                Vector(arrayOf(0.0, 0.0, 1.0)),
                Vector(arrayOf(0.0, 1.0, 0.0)),
                Vector(arrayOf(1.0, 0.0, 0.0))
            )
        )
        val b = Vector(arrayOf(1.0, 1.0, 1.0))
        val res = Systems(m, b).gauss()
        Assertions.assertEquals(
            MethodResult(Vector(arrayOf(1.0, 1.0, 1.0)), 3),
            res
        )
    }
}