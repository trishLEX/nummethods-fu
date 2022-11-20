package ru.fa.equation

import ru.fa.model.Method
import ru.fa.model.MethodResult
import ru.fa.model.Value
import ru.fa.model.Value.Companion.abs
import ru.fa.model.div
import ru.fa.model.minus

class ImprovedIterations(
    private val function: (Value) -> Value,
    private val derivative: (Value) -> Value,
    private val start: Value,
    private val end: Value,
    private val accuracy: Double
) : Method<Value> {

    private val alpha: (Value, Value) -> Value = {
            left: Value, right: Value -> 1.0 / (1.0 - (function(right) - function(left)) / (right - left))
    }
    private val f: (Value) -> Value = { x: Value -> function(x) + x }
    private val step: (Value, Value) -> Value = {
            left: Value, right: Value -> right + alpha(left, right) * (f(right) - right)
    }

    override fun evaluate(): MethodResult<Value> {
        val maxDerivative = Value.max(derivative(start), derivative(end))
        if (abs(maxDerivative).value > 1) {
            throw IllegalArgumentException()
        }

        var left = step(start, start + accuracy) //n-1
        var right = step(start + accuracy, left) //n

        var n = 1
        while (abs(right - left).value / 2 > accuracy) {
            val newLeft = step(left, right) //n +1
            right = step(right, newLeft)
            left = newLeft
            n++
        }
        return MethodResult((right - left) / 2, n)
    }
}
