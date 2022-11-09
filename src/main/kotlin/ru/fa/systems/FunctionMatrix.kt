package ru.fa.systems

data class FunctionMatrix(val m: Array<FunctionVector>) {

    constructor(n: Int): this(Array(n) { FunctionVector(it) })

    operator fun invoke(vec: Vector): Matrix {
        return Matrix(
            m.map { it(vec) }.toTypedArray()
        )
    }

    /**
     * Возвращает i-ую строку матрицы
     */
    operator fun get(i: Int): FunctionVector {
        return m[i]
    }

    /**
     * Размер матрицы
     */
    fun size(): Int {
        return m[0].size()
    }

    fun row(i: Int): FunctionVector {
        return get(i)
    }

    fun column(i: Int): FunctionVector {
        val res = FunctionVector(size())
        for (row in 0 .. size()) {
            res[row] = m[row][i]
        }
        return res
    }

    fun changeRows(i: Int, j: Int): FunctionMatrix {
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

    operator fun set(i: Int, value: FunctionVector) {
        m[i] = value
    }
}