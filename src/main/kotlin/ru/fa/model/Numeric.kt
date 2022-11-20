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

//    operator fun Double.div(n: T): T
//
//    operator fun Double.times(n: T): T
//
//    operator fun Double.minus(n: T): T
//
//    operator fun Double.plus(n: T): T
//
//    operator fun Int.div(n: T): T {
//        return this.toDouble() / n
//    }
//
//    operator fun Int.times(n: T): T {
//        return this.toDouble() * n
//    }
//
//    operator fun Int.minus(n: T): T {
//        return this.toDouble() - n
//    }
//
//    operator fun Int.plus(n: T): T {
//        return this.toDouble() + n
//    }

    abstract operator fun <T: Numeric<T>> Double.div(t: T): T
}

@Suppress("UNCHECKED_CAST")
class NumericType<T: Numeric<T>>(val clazz: Class<T>) {

    companion object {
        val VALUE = NumericType(ValueNew::class.java)
        val FUNCTION_OF_VALUE = NumericType(Function::class.java as Class<Function<ValueNew>>)
        val VECTOR_VALUE = NumericType(Vector::class.java as Class<Vector<ValueNew>>)
        val MATRIX_VALUE = NumericType(Matrix::class.java as Class<Matrix<ValueNew>>)
    }
}