package ru.fa.systems.nonlinear

import org.junit.jupiter.api.Test
import ru.fa.systems.Function
import ru.fa.systems.FunctionMatrix
import ru.fa.systems.FunctionVector
import ru.fa.systems.Matrix
import ru.fa.systems.Vector

class NonLinearNewtonRaphsonTest {

    @Test
    fun test() {
        val res = NonLinearNewtonRaphson(
            FunctionVector(arrayOf(
                Function { it[0] * it[0] + it[1] * it[1] - 1 },
                Function { it[0] * it[0] - it[1] * it[1] - 1 }
            )),
            FunctionMatrix(
                arrayOf(
                    FunctionVector(arrayOf(Function { 2 * it[0] }, Function { 2 * it[1] })),
                    FunctionVector(arrayOf(Function { 2 * it[0] }, Function { -2 * it[1] }))
                )
            ),
            Vector(arrayOf(-10.0, -1.0)),
            0.001
        ).evaluate()
        println(res)
    }

    @Test
    fun testInverseMatrix() {
        val m = Matrix(arrayOf(
            Vector(2.0, 5.0, 7.0),
            Vector(6.0, 3.0, 4.0),
            Vector(5.0, -2.0, -3.0)
        ))
        println(m.inverse())
    }

    @Test
    fun testMul() {
        val m = Matrix(arrayOf(
            Vector(1.0, 2.0),
            Vector(3.0, 4.0)
        ))
        val v = Vector(5.0, 6.0)
        println(m * v)
    }
}