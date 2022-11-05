package ru.fa.systems

data class Matrix(val m: Array<Vector>) {

    /**
     * Возвращает i-ую строку матрицы
     */
    operator fun get(i: Int): Vector {
        return m[i]
    }

    /**
     * Размер матрицы
     */
    fun size(): Int {
        return m[0].size()
    }

    fun row(i: Int): Vector {
        return get(i)
    }

    fun column(i: Int): Vector {
        val res = Vector(Array(size()) { 0.0 })
        for (row in 0 .. size()) {
            res[row] = m[row][i]
        }
        return res
    }

    fun changeRows(i: Int, j: Int): Matrix {
        val res = copy()
        val rowI = res[i]
        val rowJ = res[j]
        res[i] = rowJ
        res[j] = rowI
        return res
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is Matrix) return false

        if (!m.contentEquals(other.m)) return false

        return true
    }

    override fun hashCode(): Int {
        return m.contentHashCode()
    }

    operator fun set(i: Int, value: Vector) {
        m[i] = value
    }
}