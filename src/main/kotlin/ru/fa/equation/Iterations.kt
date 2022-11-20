package ru.fa.equation

import ru.fa.model.Method
import ru.fa.model.MethodResult
import ru.fa.model.Value
import ru.fa.model.Value.Companion.abs

class Iterations(
    private val function: (Value) -> Value,
    private val derivative: (Value) -> Value,
    private val start: Value,
    private val end: Value,
    private val accuracy: Double
) : Method<Value> {

    private val f: (Value) -> Value = { x: Value -> function(x) + x}

    override fun evaluate(): MethodResult<Value> {
        val maxDerivative = Value.max(derivative(start), derivative(end))
        if (abs(maxDerivative).value > 1) {
            throw IllegalArgumentException()
        }

        var left = f((end - start) / 2)
        var right = f(left)
        var n = 1
        while (abs(right - left).value / 2 > accuracy) {
            left = f(right)
            right = f(left)
            n++
        }
        return MethodResult((right - left) / 2, n)
    }

}