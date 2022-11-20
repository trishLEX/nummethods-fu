package ru.fa.systems

data class Vector(val v: Array<Double>) {

    constructor(vararg v: Double) : this(v.toTypedArray())

    constructor(n: Int): this(Array(n) { 0.0 })

    /**
     * Получить i-ый эелемент в векторе
     */
    operator fun get(i: Int): Double {
        return v[i]
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
    fun changeElements(i: Int, j: Int): Vector {
        val res = copy()
        val temp = res[i]
        res[i] = res[j]
        res[j] = temp
        return res
    }

    /**
     * Поделить вектор на число
     */
    operator fun div(t: Double): Vector {
        val new = v.map { it / t }.toTypedArray()
        return Vector(new)
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is Vector) return false

        if (!v.contentEquals(other.v)) return false

        return true
    }

    override fun hashCode(): Int {
        return v.contentHashCode()
    }

    /**
     * Установить i-ый элемент вектора
     */
    operator fun set(i: Int, value: Double) {
        v[i] = value
    }

    /**
     * Умножить вектор на число
     */
    operator fun times(d: Double): Vector {
        return Vector(v.map { it * d }.toTypedArray())
    }

    /**
     * Отнять от вектора другой вектор
     */
    operator fun minus(vec: Vector): Vector {
        val res = Vector(vec.size())
        for (i in 0 until size()) {
            res[i] = this[i] - vec[i]
        }
        return res
    }

    /**
     * Максимальное значение в векторе
     */
    fun max(): Double {
        return v.max()
    }

    /**
     * Вектор, состоящий из значений оргинального вектора по модулю
     */
    fun abs(): Vector {
        return Vector(v.map { kotlin.math.abs(it) }.toTypedArray())
    }
}