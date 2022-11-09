package ru.fa.systems.nonlinear

import org.junit.jupiter.api.Test
import ru.fa.systems.Function
import ru.fa.systems.FunctionMatrix
import ru.fa.systems.FunctionVector
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
            Vector(arrayOf(0.0, 0.0))
        ).evaluate()
        println(res)
    }
}