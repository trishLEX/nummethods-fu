package ru.fa.equation

import ru.fa.Method
import ru.fa.MethodResult
import ru.fa.Value
import ru.fa.Value.Companion.abs

class NewtonRaphson(
    private val function: (Value) -> Value,
    private val derivative: (Value) -> Value,
    private val start: Value,
    private val accuracy: Double = 0.001
) : Method {

    private val step: (Value) -> Value = {x: Value -> x - function(x) / derivative(x)}

    override fun evaluate(): MethodResult {
        var start = start
        var new = step(start)
        var n = 1
        while (abs(new - start).value / 2 > accuracy) {
            start = step(new)
            new = step(start)
            n++
        }
        return MethodResult((new - start) / 2, n)
    }
}