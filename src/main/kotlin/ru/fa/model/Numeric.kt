package ru.fa.model

abstract class Numeric<T: Numeric<T>>(val numericType: NumericType<T>) : Comparable<T> {

    abstract operator fun times(d: Double): T

    operator fun times(i: Int): T {
        return times(i.toDouble())
    }

    abstract operator fun div(d: Double): T

    operator fun div(i: Int): T {
        return div(i.toDouble())
    }

    abstract operator fun plus(d: Double): T

    abstract operator fun minus(d: Double): T

    abstract operator fun minus(n: T): T

    abstract operator fun plus(n: T): T

    abstract operator fun times(n: T): T

    abstract operator fun div(n: T): T

    abstract operator fun unaryMinus(): T

    abstract fun abs(): T

    abstract fun one(): T

    abstract fun zero(): T

    abstract fun self(): T

    fun pow(d: Int): T {
        if (d < 0) {
            throw IllegalArgumentException()
        } else if (d == 0) {
            return one()
        } else if (d == 1) {
            return self()
        }

        var res = self()
        for (i in 1 until d) {
            res *= res
        }
        return res
    }

    abstract fun copy(): T

    abstract operator fun <T: Numeric<T>> Double.div(t: T): T
}

@Suppress("UNCHECKED_CAST")
class NumericType<T: Numeric<T>>(val clazz: Class<T>) {

    companion object {
        val VALUE = NumericType(Value::class.java)
        val POINT = NumericType(Point::class.java)
        val FUNCTION_VALUE = NumericType(Function::class.java as Class<Function<Value>>)
        val FUNCTION_VECTOR = NumericType(Function::class.java as Class<Function<Vector<Value>>>)
        val VECTOR_VALUE = NumericType(Vector::class.java as Class<Vector<Value>>)
        val VECTOR_FUNCTION_VECTOR = NumericType(Vector::class.java as Class<Vector<Function<Vector<Value>>>>)
        val MATRIX_VALUE = NumericType(Matrix::class.java as Class<Matrix<Value>>)
        val MATRIX_FUNCTION_VECTOR = NumericType(Matrix::class.java as Class<Matrix<Function<Vector<Value>>>>)
    }
}