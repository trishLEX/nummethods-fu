package ru.fa.systems.nonlinear

import ru.fa.model.Matrix
import ru.fa.model.Method
import ru.fa.model.MethodResult
import ru.fa.model.NumericType
import ru.fa.model.Value

class NonLinearNewtonRaphson(
    private val function: ru.fa.model.Vector<ru.fa.model.Function<ru.fa.model.Vector<Value>>>,
    private val jacobian: Matrix<ru.fa.model.Function<ru.fa.model.Vector<Value>>>,
    private val start: ru.fa.model.Vector<Value>,
    private val accuracy: Double = 0.001
) : Method<ru.fa.model.Vector<Value>> {

    private val step: (ru.fa.model.Vector<Value>) -> ru.fa.model.Vector<Value> = { x: ru.fa.model.Vector<Value> -> x - jacobian(x).inverse() * function(x)}

    private fun jacobian(x: ru.fa.model.Vector<Value>): Matrix<Value> {
        return Matrix(
            jacobian.m.map { ru.fa.model.Vector(it.v.map { it(x) }.toTypedArray(), NumericType.VECTOR_VALUE) }.toTypedArray(),
            NumericType.MATRIX_VALUE
        )
    }

    private fun function(x: ru.fa.model.Vector<Value>): ru.fa.model.Vector<Value> {
        return ru.fa.model.Vector(function.v.map { it(x) }.toTypedArray(), NumericType.VECTOR_VALUE)
    }

    override fun evaluate(): MethodResult<ru.fa.model.Vector<Value>> {
        var start = start
        var new = step(start)
        var n = 1
        while ((new - start).abs().max().value > accuracy) {
            start = new
            new = step(start)
            n++
        }
        return MethodResult(new, n)
    }
}