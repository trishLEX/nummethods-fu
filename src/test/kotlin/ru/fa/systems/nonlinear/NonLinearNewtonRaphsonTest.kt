package ru.fa.systems.nonlinear

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import ru.fa.model.Function
import ru.fa.model.Matrix
import ru.fa.model.NumericType
import ru.fa.model.Vector

class NonLinearNewtonRaphsonTest {

    @Test
    fun test() {
        val res = NonLinearNewtonRaphson(
            Vector.Companion.createVector(
                Function(NumericType.FUNCTION_VECTOR) { it[0] * it[0] + it[1] * it[1] - 1.0 },
                Function(NumericType.FUNCTION_VECTOR) { it[0] * it[0] - it[1] * it[1] - 1.0 },
            ),
            Matrix.createMatrix(
                Vector.Companion.createVector(
                    Function(NumericType.FUNCTION_VECTOR) { it[0] * 2 },
                    Function(NumericType.FUNCTION_VECTOR) { it[1] * 2 },
                ),
                Vector.Companion.createVector(
                    Function(NumericType.FUNCTION_VECTOR) {   it[0] * 2 },
                    Function(NumericType.FUNCTION_VECTOR) { - it[1] * 2 },
                ),
            ),
            Vector.Companion.createVector(-10.0, -1.0)
        ).evaluate()
        Assertions.assertEquals(
            Vector.createVector(-1.0, -9.765625E-4),
            res.result
        )
    }

    @Test
    fun testInverseMatrix() {
        val m = Matrix.createValueMatrix(
            Vector.Companion.createVector(2.0, 5.0, 7.0),
            Vector.Companion.createVector(6.0, 3.0, 4.0),
            Vector.Companion.createVector(5.0, -2.0, -3.0)
        )
        println(m.inverse())
    }

    @Test
    fun testMul() {
        val m = Matrix.createValueMatrix(
            Vector.createVector(1.0, 2.0),
            Vector.createVector(3.0, 4.0)
        )
        val v = Vector.createVector(5.0, 6.0)
        println(m * v)
    }
}
