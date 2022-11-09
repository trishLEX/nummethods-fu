package ru.fa.systems.nonlinear

import ru.fa.Method
import ru.fa.MethodResult
import ru.fa.systems.FunctionMatrix
import ru.fa.systems.FunctionVector
import ru.fa.systems.Vector

class NonLinearNewtonRaphson(
    private val function: FunctionVector,
    private val jacobian: FunctionMatrix,
    private val start: Vector,
    private val accuracy: Double = 0.001
) : Method<Vector> {

    private val step: (Vector) -> Vector = { x: Vector -> x - jacobian(x).inverse() * function(x)}

    override fun evaluate(): MethodResult<Vector> {
        var start = start
        var new = step(start)
        var n = 1
        while ((new - start).max() > accuracy) {
            start = step(new)
            new = step(start)
            n++
        }
        return MethodResult((new - start) / 2.0, n)
    }
}