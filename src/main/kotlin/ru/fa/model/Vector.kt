package ru.fa.model

class Vector<T: Numeric<T>>(val v: Array<T>, numericType: NumericType<Vector<T>>): Numeric<Vector<T>>(numericType) {

    companion object {
        fun createVector(vararg v: Double): Vector<ValueNew> {
            return Vector(v.map { ValueNew(it) }.toTypedArray(), NumericType.VECTOR_VALUE)
        }

        fun createVector(n: Int): Vector<ValueNew> {
            return Vector(Array(n) {ValueNew.ZERO}, NumericType.VECTOR_VALUE)
        }

        fun <T: Numeric<T>> zero(n: Int, numericType: NumericType<Vector<T>>): Vector<T> {
            return when (numericType) {
                NumericType.VECTOR_VALUE -> Vector(Array(n) {ValueNew.ZERO}, numericType) as Vector<T>
                else -> throw UnsupportedOperationException()
            }
        }

        fun <T: Numeric<T>> one(n: Int, numericType: NumericType<Vector<T>>): Vector<T> {
            return when (numericType) {
                NumericType.VECTOR_VALUE -> Vector(Array(n) {ValueNew.ONE}, numericType) as Vector<T>
                else -> throw UnsupportedOperationException()
            }
        }
    }

    /**
     * Получить i-ый эелемент в векторе
     */
    operator fun get(i: Int): T {
        return v[i]
    }

    /**
     * Установить i-ый элемент вектора
     */
    operator fun set(i: Int, value: T) {
        v[i] = value
    }

    /**
     * Размер вектора
     */
    fun size(): Int {
        return v.size
    }

    /**
     * Поменять элементы в векторе местами
     */
    fun changeElements(i: Int, j: Int): Vector<T> {
        val res = copy()
        val temp = res[i]
        res[i] = res[j]
        res[j] = temp
        return res
    }

    fun max(): T {
        return v.max()
    }

    /**
     * Вектор, состоящий из значений оргинального вектора по модулю
     */
    override fun abs(): Vector<T> {
        val array = v.copyOf()
        for (i in array.indices) {
            array[i] = array[i].abs()
        }
        return Vector(array, numericType)
    }

    /**
     * Умножить вектор на число
     */
    override fun times(d: Double): Vector<T> {
        val array = v.copyOf()
        for (i in array.indices) {
            array[i] = array[i] * d
        }
        return Vector(array, numericType)
    }

    /**
     * Поделить вектор на число
     */
    override fun div(d: Double): Vector<T> {
        val array = v.copyOf()
        for (i in array.indices) {
            array[i] = array[i] / d
        }
        return Vector(array, numericType)
    }

    override fun <T : Numeric<T>> Double.div(t: T): T {
        throw UnsupportedOperationException()
//        val array = v.copyOf()
//        for (i in array.indices) {
//            array[i] = t / array[i]
//        }
//        return Vector(array, numericType) as T
    }

    /**
     * Прибавить к вектору число
     */
    override fun plus(d: Double): Vector<T> {
        val array = v.copyOf()
        for (i in array.indices) {
            array[i] = array[i] + d
        }
        return Vector(array, numericType)
    }

    /**
     * Отнять от вектора число
     */
    override fun minus(d: Double): Vector<T> {
        val array = v.copyOf()
        for (i in array.indices) {
            array[i] = array[i] - d
        }
        return Vector(array, numericType)
    }

    /**
     * Вычетание векторов
     */
    override fun minus(n: Vector<T>): Vector<T> {
        if (size() != n.size()) {
            throw IllegalArgumentException()
        }
        val array = v.copyOf()
        for (i in array.indices) {
            array[i] = array[i] - n[i]
        }
        return Vector(array, numericType)
    }

    /**
     * Сложение векторов
     */
    override fun plus(n: Vector<T>): Vector<T> {
        if (size() != n.size()) {
            throw IllegalArgumentException()
        }
        val array = v.copyOf()
        for (i in array.indices) {
            array[i] = array[i] + n[i]
        }
        return Vector(array, numericType)
    }

    override fun div(n: Vector<T>): Vector<T> {
        throw UnsupportedOperationException()
    }

    override fun times(n: Vector<T>): Vector<T> {
        throw UnsupportedOperationException()
    }

    override fun unaryMinus(): Vector<T> {
        val array = v.copyOf()
        for (i in array.indices) {
            array[i] = -array[i]
        }
        return Vector(array, numericType)
    }

    @Suppress("UNCHECKED_CAST")
    override fun one(): Vector<T> {
        return when (numericType) {
            NumericType.VECTOR_VALUE -> Vector(Array(size()) {ValueNew.ONE}, numericType) as Vector<T>
            else -> throw UnsupportedOperationException()
        }
    }

    @Suppress("UNCHECKED_CAST")
    override fun zero(): Vector<T> {
        return when (numericType) {
            NumericType.VECTOR_VALUE -> Vector(Array(size()) {ValueNew.ZERO}, numericType) as Vector<T>
            else -> throw UnsupportedOperationException()
        }
    }

    override fun self(): Vector<T> {
        return this
    }

    override fun compareTo(other: Vector<T>): Int {
        throw UnsupportedOperationException()
    }

    override fun copy(): Vector<T> {
        val copy = zero()
        for (i in v.indices) {
            copy[i] = this[i]
        }
        return copy
    }

    operator fun times(t: T): Vector<T> {
        val copy = copy()
        for (i in v.indices) {
            copy[i] = this[i] * t
        }
        return copy
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Vector<*>

        if (!v.contentEquals(other.v)) return false

        return true
    }

    override fun hashCode(): Int {
        return v.contentHashCode()
    }

    override fun toString(): String {
        return "Vector(v=${v.contentToString()})"
    }


}