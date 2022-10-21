package ru.fa.equation

import ru.fa.Method
import ru.fa.MethodResult
import ru.fa.Value
import ru.fa.Value.Companion.abs

class Iterations(
    private val function: (Value) -> Value,
    private val derivative: (Value) -> Value,
    private val start: Value,
    private val end: Value,
    private val accuracy: Double = 0.001
) : Method {

    private val f: (Value) -> Value = {x: Value -> function(x) + x}

    override fun evaluate(): MethodResult {
        val maxDerivative = Value.max(derivative(start), derivative(end))
        if (abs(maxDerivative).value > 1) {
            throw IllegalArgumentException()
        }

        var left = f((end - start) / 2)
        var right = f(left)
        while (abs(right - left).value > accuracy) {
            left = f(right)
            right = f(left)
        }
        return MethodResult((right - left) / 2, methodError())
    }

    override fun methodError(): Double {
        return 0.0
    }


}