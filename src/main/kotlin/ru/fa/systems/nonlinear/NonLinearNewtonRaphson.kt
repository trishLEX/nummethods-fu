package ru.fa.systems.nonlinear

import ru.fa.model.Function
import ru.fa.model.Matrix
import ru.fa.model.Method
import ru.fa.model.MethodResult
import ru.fa.model.NumericType
import ru.fa.model.Value
import ru.fa.model.Vector

class NonLinearNewtonRaphson(
    private val function: Vector<Function<Vector<Value>>>,
    private val jacobian: Matrix<Function<Vector<Value>>>,
    private val start: Vector<Value>,
    private val accuracy: Double = 0.001
) : Method<Vector<Value>> {

    private val step: (Vector<Value>) -> Vector<Value> = { x: Vector<Value> -> x - jacobian(x).inverse() * function(x)}

    private fun jacobian(x: Vector<Value>): Matrix<Value> {
        return Matrix.createValueMatrix(
            jacobian.m.map { Vector.createVector(it.v.map { it(x)}) }
        )
    }

    private fun function(x: Vector<Value>): Vector<Value> {
        return Vector(function.v.map { it(x) }.toTypedArray(), NumericType.VECTOR_VALUE)
    }

    override fun evaluate(): MethodResult<Vector<Value>> {
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