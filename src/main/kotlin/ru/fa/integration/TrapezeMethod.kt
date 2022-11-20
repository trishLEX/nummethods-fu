package ru.fa.integration

import ru.fa.model.Method
import ru.fa.model.MethodResult
import ru.fa.model.Value
import ru.fa.model.Value.Companion.abs
import ru.fa.model.times

class TrapezeMethod(
    private val function: (Value) -> Value,
    private val derivative: (Value) -> Value,
    private val start: Value,
    private val end: Value,
    private val n: Int,
) : Method<Value> {

    private val h = abs((start - end) / n)

    override fun evaluate(): MethodResult<Value> {
        var sum = Value(0.0, 0.0)
        for (i in 0 ..n) {
            val x = start + h * i
            sum += if (i == 0 || i == n) {
                function(x)
            } else {
                2 * function(x)
            }
        }
        sum *= h / 2

        return MethodResult(sum, methodError(), n)
    }

    override fun methodError(): Double {
        return (- h * h / 12 * (derivative(end) - derivative(start))).value
    }

}