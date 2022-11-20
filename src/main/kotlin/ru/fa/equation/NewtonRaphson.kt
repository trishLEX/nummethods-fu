package ru.fa.equation

import ru.fa.model.Method
import ru.fa.model.MethodResult
import ru.fa.model.Value
import ru.fa.model.Value.Companion.abs

class NewtonRaphson(
    private val function: (Value) -> Value,
    private val derivative: (Value) -> Value,
    private val start: Value,
    private val accuracy: Double
) : Method<Value> {

    private val step: (Value) -> Value = { x: Value -> x - function(x) / derivative(x)}

    override fun evaluate(): MethodResult<Value> {
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