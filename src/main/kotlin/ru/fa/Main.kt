package ru.fa

import kotlin.math.abs


private val function: (Value) -> Value = {x -> 7.0 / (x * x + 1.0)}

private val difFunction: (Value) -> Value = {x -> -14.0 * x / (x * x + 1.0).pow(2)}

private val difDifFunction: (Value) -> Value = {
    x -> 14.0 * (3.0 * x * x - 1.0 ) / (x * x + 1.0).pow(3)
}

private const val e = 0.01
private val start = Value(0.0, e)
private val end = Value(5.0, e)
private const val n = 10
private val h = abs((start - end) / n)
//private const val memberCount = 3



fun main(args: Array<String>) {
    val square = trapeze()
    val absError = absError().value
    val absError2 = absError2().value
    val error = square.error + absError
    println("square = ${square.value}, absError = $absError, error = $error")
}

fun trapeze(): Value {
    var sum = Value(0.0, 0.0)
    for (i in 0 .. n) {
        val x = start + h * i
        if (i == 0 || i == n) {
            var f = function(x)
            sum += function(x)
        } else {
            sum += 2 * function(x)
        }
    }
    sum *= h / 2
    return sum
}

fun absError(): Value {
    return - h * h / 12 * (difFunction(end) - difFunction(start))
}

fun absError2(): Value {
    return 7 / 2 * n * h.pow(3) / 24
}

data class Value(val value: Double, val error: Double) {

    operator fun plus(increment: Value): Value {
        return Value(
            value + increment.value,
            error + increment.error
        )
    }

    operator fun minus(decrement: Value): Value {
        return Value(
            value - decrement.value,
            error - decrement.error
        )
    }

    operator fun times(mul: Value): Value {
        val e1 = if (value == 0.0) 1.0 else error / value
        val e2 = if (mul.value == 0.0) 1.0 else mul.error / mul.value
        return Value(
            value * mul.value,
            e1 + e2
        )
    }

    operator fun times(i: Int): Value {
        return Value(value * i, error)
    }

    operator fun div(div: Value): Value {
        val e1 = if (value == 0.0) 1.0 else error / value
        val e2 = if (div.value == 0.0) 1.0 else div.error / div.value
        return Value(
            value / div.value,
            e1 - e2
        )
    }

    operator fun div(div: Double): Value {
        return Value(value / div, error)
    }

    operator fun div(div: Int): Value {
        return Value(value / div, error)
    }

    operator fun plus(i: Double): Value {
        return Value(value + i, error)
    }

    operator fun minus(d: Double): Value {
        return Value(value - d, error)
    }

    fun pow(d: Int): Value {
        if (d < 0) {
            throw IllegalArgumentException()
        } else if (d == 0) {
            return Value(1.0, error)
        } else if (d == 1) {
            return Value(value, error)
        }

        var res = this
        for (i in 1 until d) {
            res *= res
        }
        return res
    }

    operator fun unaryMinus(): Value {
        return Value(-value, error)
    }
}

operator fun Double.div(value: Value): Value {
    return Value(this / value.value, value.error)
}

private operator fun Double.times(value: Value): Value {
    return Value(this * value.value, value.error)
}

private operator fun Int.times(value: Value): Value {
    return value * this
}

fun abs(value: Value): Value {
    return Value(abs(value.value), abs(value.error))
}