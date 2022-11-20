package ru.fa.systems.nonlinear

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import ru.fa.model.NumericType

class NonLinearNewtonRaphsonTest {

    @Test
    fun test() {
        val res = NonLinearNewtonRaphson(
            ru.fa.model.Vector.Companion.createVector(
                ru.fa.model.Function(NumericType.FUNCTION_VECTOR) { it[0] * it[0] + it[1] * it[1] - 1.0 },
                ru.fa.model.Function(NumericType.FUNCTION_VECTOR) { it[0] * it[0] - it[1] * it[1] - 1.0 },
            ),
            ru.fa.model.Matrix.createMatrix(
                ru.fa.model.Vector.Companion.createVector(
                    ru.fa.model.Function(NumericType.FUNCTION_VECTOR) { it[0] * 2 },
                    ru.fa.model.Function(NumericType.FUNCTION_VECTOR) { it[1] * 2 },
                ),
                ru.fa.model.Vector.Companion.createVector(
                    ru.fa.model.Function(NumericType.FUNCTION_VECTOR) {   it[0] * 2 },
                    ru.fa.model.Function(NumericType.FUNCTION_VECTOR) { - it[1] * 2 },
                ),
            ),
            ru.fa.model.Vector.Companion.createVector(-10.0, -1.0)
        ).evaluate()
        Assertions.assertEquals(
            ru.fa.model.Vector.createVector(-1.0, -9.765625E-4),
            res.result
        )
    }

    @Test
    fun testInverseMatrix() {
        val m = ru.fa.model.Matrix.createValueMatrix(
            ru.fa.model.Vector.Companion.createVector(2.0, 5.0, 7.0),
            ru.fa.model.Vector.Companion.createVector(6.0, 3.0, 4.0),
            ru.fa.model.Vector.Companion.createVector(5.0, -2.0, -3.0)
        )
        println(m.inverse())
    }

    @Test
    fun testMul() {
        val m = ru.fa.model.Matrix.createValueMatrix(
            ru.fa.model.Vector.createVector(1.0, 2.0),
            ru.fa.model.Vector.createVector(3.0, 4.0)
        )
        val v = ru.fa.model.Vector.createVector(5.0, 6.0)
        println(m * v)
    }
}
