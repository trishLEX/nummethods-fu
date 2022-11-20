package ru.fa.systems

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import ru.fa.model.MethodResult
import ru.fa.model.NumericType

class SystemsTest {

    @Test
    fun testGauss() {
        val m = ru.fa.model.Matrix(
            arrayOf(
                ru.fa.model.Vector.createVector(2.0, -1.0, 5.0),
                ru.fa.model.Vector.createVector(1.0, 1.0, -3.0),
                ru.fa.model.Vector.createVector(2.0, 4.0, 1.0)
            ),
            NumericType.MATRIX_VALUE
        )
        val b = ru.fa.model.Vector.createVector(10.0, -2.0, 1.0)
        val res = Systems(m, b).gauss()
        Assertions.assertEquals(
            MethodResult(ru.fa.model.Vector.createVector(2.0, -1.0, 1.0), 3),
            res
        )
    }

    @Test
    fun testGauss2() {
        val m = ru.fa.model.Matrix(
            arrayOf(
                ru.fa.model.Vector.createVector(4.0, 2.0, -1.0),
                ru.fa.model.Vector.createVector(5.0, 3.0, -2.0),
                ru.fa.model.Vector.createVector(3.0, 2.0, -3.0)
            ),
            NumericType.MATRIX_VALUE
        )
        val b = ru.fa.model.Vector.createVector(1.0, 2.0, 0.0)
        val res = Systems(m, b).gauss()
        Assertions.assertEquals(
            MethodResult(ru.fa.model.Vector.createVector(-1.0, 3.0, 1.0), 3),
            res
        )
    }

    @Test
    fun testChangeMainRow() {
        val m = ru.fa.model.Matrix(
            arrayOf(
                ru.fa.model.Vector.createVector(0.0, 0.0, 1.0),
                ru.fa.model.Vector.createVector(0.0, 1.0, 0.0),
                ru.fa.model.Vector.createVector(1.0, 0.0, 0.0)
            ),
            NumericType.MATRIX_VALUE
        )
        val b = ru.fa.model.Vector.createVector(1.0, 1.0, 1.0)
        val res = Systems(m, b).gauss()
        Assertions.assertEquals(
            MethodResult(ru.fa.model.Vector.createVector(1.0, 1.0, 1.0), 3),
            res
        )
    }
}