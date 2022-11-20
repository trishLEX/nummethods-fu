package ru.fa.model

data class ValueNew(val value: Double, val error: Double) : Numeric<ValueNew>(NumericType.VALUE) {

    companion object {
        val ONE = ValueNew(1.0)
        val ZERO = ValueNew(0.0)

        fun max(a: ValueNew, b: ValueNew): ValueNew {
            return if (a.value >= b.value) {
                a
            } else {
                b
            }
        }

        fun abs(value: ValueNew): ValueNew {
            return ValueNew(kotlin.math.abs(value.value), kotlin.math.abs(value.error))
        }
    }

    constructor(value: Double): this(value, 0.0)

    override fun times(d: Double): ValueNew {
        return ValueNew(value * d, error)
    }

    override fun times(n: ValueNew): ValueNew {
        val e1 = if (value == 0.0) 1.0 else error / value
        val e2 = if (n.value == 0.0) 1.0 else n.error / n.value
        return ValueNew(
            value * n.value,
            e1 + e2
        )
    }

    override fun div(d: Double): ValueNew {
        return ValueNew(value / d, error)
    }

    override fun div(n: ValueNew): ValueNew {
        val e1 = if (value == 0.0) 1.0 else error / value
        val e2 = if (n.value == 0.0) 1.0 else n.error / n.value
        return ValueNew(
            value / n.value,
            e1 - e2
        )
    }

    override fun plus(d: Double): ValueNew {
        return ValueNew(value + d, error)
    }

    override fun plus(n: ValueNew): ValueNew {
        return ValueNew(
            value + n.value,
            error + n.error
        )
    }

    override fun minus(d: Double): ValueNew {
        return ValueNew(value - d, error)
    }

    override fun minus(n: ValueNew): ValueNew {
        return ValueNew(
            value - n.value,
            error - n.error
        )
    }

    override fun unaryMinus(): ValueNew {
        return ValueNew(-value, error)
    }

    override fun abs(): ValueNew {
        return Companion.abs(this)
    }

    override fun one(): ValueNew {
        return ONE
    }

    override fun zero(): ValueNew {
        return ZERO
    }

    override fun self(): ValueNew {
        return this
    }

    override fun compareTo(other: ValueNew): Int {
        return this.value.compareTo(other.value)
    }

    override fun copy(): ValueNew {
        return ValueNew(value, error)
    }

    override fun <T : Numeric<T>> Double.div(t: T): T {
        return ValueNew(1 / value, error) as T
    }

    override fun toString(): String {
        return "ValueNew(value=$value, error=$error)"
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as ValueNew

        if (value != other.value) return false

        return true
    }

    override fun hashCode(): Int {
        return value.hashCode()
    }

}

operator fun Int.times(v: ValueNew): ValueNew {
   return this.toDouble() * v
}

operator fun Double.div(n: ValueNew): ValueNew {
    return ValueNew(this / n.value, n.error)
}

operator fun Double.times(n: ValueNew): ValueNew {
    return ValueNew(this * n.value, n.error)
}

operator fun Double.minus(n: ValueNew): ValueNew {
    return ValueNew(this - n.value, n.error)
}

operator fun Double.plus(n: ValueNew): ValueNew {
    return ValueNew(this + n.value, n.error)
}
