package ru.fa

data class Value(val value: Double, val error: Double) {

    companion object {
        fun max(a: Value, b: Value): Value {
            return if (a.value >= b.value) {
                a
            } else {
                b
            }
        }

        fun abs(value: Value): Value {
            return Value(kotlin.math.abs(value.value), kotlin.math.abs(value.error))
        }
    }

    /**
     * Операция сложения неточных чисел
     */
    operator fun plus(increment: Value): Value {
        return Value(
            value + increment.value,
            error + increment.error
        )
    }

    /**
     * Операция вычетания неточных чисел
     */
    operator fun minus(decrement: Value): Value {
        return Value(
            value - decrement.value,
            error - decrement.error
        )
    }

    /**
     * Операция умножения неточных чисел
     */
    operator fun times(mul: Value): Value {
        val e1 = if (value == 0.0) 1.0 else error / value
        val e2 = if (mul.value == 0.0) 1.0 else mul.error / mul.value
        return Value(
            value * mul.value,
            e1 + e2
        )
    }

    /**
     * Операция деления неточных чисел
     */
    operator fun div(div: Value): Value {
        val e1 = if (value == 0.0) 1.0 else error / value
        val e2 = if (div.value == 0.0) 1.0 else div.error / div.value
        return Value(
            value / div.value,
            e1 - e2
        )
    }

    operator fun times(i: Int): Value {
        return Value(value * i, error)
    }

    operator fun times(d: Double): Value {
        return Value(value * d, error)
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

operator fun Double.times(value: Value): Value {
    return value * this
}

operator fun Int.times(value: Value): Value {
    return value * this
}