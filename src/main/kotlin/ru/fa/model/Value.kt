package ru.fa.model

data class Value(val value: Double, val error: Double) : Numeric<Value>(NumericType.VALUE) {

    companion object {
        val ONE = Value(1.0)
        val ZERO = Value(0.0)

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

    constructor(value: Double): this(value, 0.0)

    override fun times(d: Double): Value {
        return Value(value * d, error)
    }

    override fun times(n: Value): Value {
        val e1 = if (value == 0.0) 1.0 else error / value
        val e2 = if (n.value == 0.0) 1.0 else n.error / n.value
        return Value(
            value * n.value,
            e1 + e2
        )
    }

    override fun div(d: Double): Value {
        return Value(value / d, error)
    }

    override fun div(n: Value): Value {
        val e1 = if (value == 0.0) 1.0 else error / value
        val e2 = if (n.value == 0.0) 1.0 else n.error / n.value
        return Value(
            value / n.value,
            e1 - e2
        )
    }

    override fun plus(d: Double): Value {
        return Value(value + d, error)
    }

    override fun plus(n: Value): Value {
        return Value(
            value + n.value,
            error + n.error
        )
    }

    override fun minus(d: Double): Value {
        return Value(value - d, error)
    }

    override fun minus(n: Value): Value {
        return Value(
            value - n.value,
            error - n.error
        )
    }

    override fun unaryMinus(): Value {
        return Value(-value, error)
    }

    override fun abs(): Value {
        return Companion.abs(this)
    }

    override fun one(): Value {
        return ONE
    }

    override fun zero(): Value {
        return ZERO
    }

    override fun self(): Value {
        return this
    }

    override fun compareTo(other: Value): Int {
        return this.value.compareTo(other.value)
    }

    override fun copy(): Value {
        return Value(value, error)
    }

    override fun <T : Numeric<T>> Double.div(t: T): T {
        return Value(1 / value, error) as T
    }

    override fun toString(): String {
        return "ValueNew(value=$value, error=$error)"
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Value

        if (value != other.value) return false

        return true
    }

    override fun hashCode(): Int {
        return value.hashCode()
    }

}

operator fun Int.times(v: Value): Value {
   return this.toDouble() * v
}

operator fun Double.div(n: Value): Value {
    return Value(this / n.value, n.error)
}

operator fun Double.times(n: Value): Value {
    return Value(this * n.value, n.error)
}

operator fun Double.minus(n: Value): Value {
    return Value(this - n.value, n.error)
}

operator fun Double.plus(n: Value): Value {
    return Value(this + n.value, n.error)
}
