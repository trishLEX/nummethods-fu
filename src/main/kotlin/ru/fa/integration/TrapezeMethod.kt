package ru.fa.integration

import ru.fa.model.Method
import ru.fa.model.MethodResult
import ru.fa.model.ValueNew
import ru.fa.model.ValueNew.Companion.abs
import ru.fa.model.times

class TrapezeMethod(
    private val function: (ValueNew) -> ValueNew,
    private val derivative: (ValueNew) -> ValueNew,
    private val start: ValueNew,
    private val end: ValueNew,
    private val n: Int,
) : Method<ValueNew> {

    private val h = abs((start - end) / n)

    override fun evaluate(): MethodResult<ValueNew> {
        var sum = ValueNew(0.0, 0.0)
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