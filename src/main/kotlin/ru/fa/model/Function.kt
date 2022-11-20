package ru.fa.model

class Function<T: Numeric<T>>(
    numericType: NumericType<Function<T>>,
    val f: (T) -> Value,
) : Numeric<Function<T>>(numericType) {

    companion object {

        fun createFunction(f: (Value) -> Value): Function<Value> {
            return Function(NumericType.FUNCTION_VALUE, f)
        }

        fun createVectorFunction(f: (Vector<Value>) -> Value): Function<Vector<Value>> {
            return Function(NumericType.FUNCTION_VECTOR, f)
        }
    }

    operator fun invoke(value: T): Value {
        return f(value)
    }

    override fun times(d: Double): Function<T> {
        return Function(numericType) { f(it) * d }
    }

    override fun div(d: Double): Function<T> {
        return Function(numericType) { f(it) / d }
    }

    override fun <T : Numeric<T>> Double.div(t: T): T {
        TODO("Not yet implemented")
    }

    override fun plus(d: Double): Function<T> {
        return Function(numericType) { f(it) + d }
    }

    override fun minus(d: Double): Function<T> {
        return Function(numericType) { f(it) - d }
    }

    override fun minus(n: Function<T>): Function<T> {
        return Function(numericType) { f(it) - n(it) }
    }

    override fun plus(n: Function<T>): Function<T> {
        return Function(numericType) { f(it) + f(it) }
    }

    override fun times(n: Function<T>): Function<T> {
        return Function(numericType) { f(it) * n(it) }
    }

    override fun div(n: Function<T>): Function<T> {
        return Function(numericType) { f(it) / n(it) }
    }

    override fun unaryMinus(): Function<T> {
        return Function(numericType) { -f(it) }
    }

    override fun abs(): Function<T> {
        return Function(numericType) { f(it).abs() }
    }

    @Suppress("UNCHECKED_CAST")
    override fun one(): Function<T> {
        return when (numericType) {
            NumericType.FUNCTION_VALUE -> createFunction { Value(1.0) } as Function<T>
            else -> throw UnsupportedOperationException()
        }
    }

    @Suppress("UNCHECKED_CAST")
    override fun zero(): Function<T> {
        return when (numericType) {
            NumericType.FUNCTION_VALUE -> createFunction { Value(0.0) } as Function<T>
            else -> throw UnsupportedOperationException()
        }
    }

    override fun self(): Function<T> {
        return this
    }

    override fun compareTo(other: Function<T>): Int {
        throw UnsupportedOperationException()
    }

    override fun copy(): Function<T> {
        return Function(numericType, f)
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Function<*>

        if (f != other.f) return false

        return true
    }

    override fun hashCode(): Int {
        return f.hashCode()
    }


}