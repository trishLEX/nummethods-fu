package ru.fa.integration

import ru.fa.Method
import ru.fa.MethodResult
import ru.fa.Value
import ru.fa.Value.Companion.abs
import ru.fa.times

class TrapezeMethod(
    private val function: (Value) -> Value,
    private val derivative: (Value) -> Value,
    private val start: Value,
    private val end: Value,
    private val n: Int,
) : Method {

    private val h = abs((start - end) / n)

    override fun evaluate(): MethodResult {
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

        return MethodResult(sum, methodError())
    }

    override fun methodError(): Double {
        return (- h * h / 12 * (derivative(end) - derivative(start))).value
    }

}