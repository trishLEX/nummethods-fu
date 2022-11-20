package ru.fa.model

import ru.fa.systems.changeSign

class Matrix<T: Numeric<T>>(
    val m: Array<Vector<T>>,
    numericType: NumericType<Matrix<T>>
): Numeric<Matrix<T>>(numericType) {

    companion object {
        fun createMatrix(n: Int): Matrix<ValueNew> {
            return Matrix(Array(n) { Vector.createVector(n)}, NumericType.MATRIX_VALUE)
        }

        @Suppress("UNCHECKED_CAST")
        fun <T: Numeric<T>> zero(n: Int, numericType: NumericType<Matrix<T>>): Matrix<T> {
            return when (numericType) {
                NumericType.MATRIX_VALUE -> Matrix(
                    Array(n) { Vector.zero(n, getVectorType(numericType))},
                    numericType
                ) as Matrix<T>
                else -> throw UnsupportedOperationException()
            }
        }

        @Suppress("UNCHECKED_CAST")
        fun <T: Numeric<T>> one(n: Int, numericType: NumericType<Matrix<T>>): Matrix<T> {
            return when (numericType) {
                NumericType.MATRIX_VALUE -> Matrix(
                    Array(n) { Vector.one(n, getVectorType(numericType))},
                    numericType
                ) as Matrix<T>
                else -> throw UnsupportedOperationException()
            }
        }

        @Suppress("UNCHECKED_CAST")
        private fun <T: Numeric<T>> getVectorType(numericType: NumericType<Matrix<T>>): NumericType<Vector<T>> {
            return when (numericType) {
                NumericType.MATRIX_VALUE -> NumericType.VECTOR_VALUE as NumericType<Vector<T>>
                else -> throw UnsupportedOperationException()
            }
        }
    }

    /**
     * Возвращает i-ую строку матрицы
     */
    operator fun get(i: Int): Vector<T> {
        return m[i]
    }

    operator fun set(i: Int, value: Vector<T>) {
        m[i] = value
    }

    /**
     * Получить i-тую строчку в матрице
     */
    fun row(i: Int): Vector<T> {
        return get(i)
    }

    /**
     * Получить i-ый столбец в матрице
     */
    @Suppress("UNCHECKED_CAST")
    fun column(i: Int): Vector<T> {
        val res = Vector(arrayOfNulls<Any?>(size()) as Array<T>, getVectorType(numericType))
        for (row in 0 .. size()) {
            res[row] = m[row][i]
        }
        return res
    }

    /**
     * Размер матрицы
     */
    fun size(): Int {
        return m[0].size()
    }

    /**
     * Поменять строки местами
     */
    fun changeRows(i: Int, j: Int): Matrix<T> {
        val res = copy()
        val rowI = res[i]
        val rowJ = res[j]
        res[i] = rowJ
        res[j] = rowI
        return res
    }

    @Suppress("UNCHECKED_CAST")
    operator fun times(vec: Vector<T>): Vector<T> {
        val res = Vector(arrayOfNulls<Any?>(size()) as Array<T>, getVectorType(numericType))
        for (i in 0 until size()) {
            val row = m[i]
            var resRow = row[0].zero()
            for (j in 0 until size()) {
                resRow += row[j] * vec[j]
            }
            res[i] = resRow
        }
        return res
    }

    fun transpose(): Matrix<T> {
        val transposedMatrix = copy()
        for (i in 0 until size()) {
            for (j in 0 until size()) {
                transposedMatrix[j][i] = this[i][j]
            }
        }
        return transposedMatrix
    }

    fun determinant(): T {
        if (size() == 1) {
            return this[0][0]
        }
        if (size() == 2) {
            return this[0][0] * this[1][1] - this[0][1] * this[1][0]
        }
        var sum = this[0][0].zero()
        for (i in 0 until size()) {
            val mul = if (i % 2 == 0) 1 else -1
            sum += this[0][i] * createSubMatrix(0, i).determinant() * mul
        }
        return sum
    }

    fun createSubMatrix(excludingRow: Int, excludingCol: Int): Matrix<T> {
        val mat = Companion.zero(size() - 1, numericType)
        var r = -1
        for (i in 0 until size()) {
            if (i == excludingRow) {
                continue
            }
            r++
            var c = -1
            for (j in 0 until size()) {
                if (j == excludingCol) {
                    continue
                }
                mat[r][++c] = this[i][j]
            }
        }
        return mat
    }

    fun cofactor(): Matrix<T> {
        val mat = copy()
        for (i in 0 until size()) {
            for (j in 0 until size()) {
                mat[i][j] = createSubMatrix(i, j).determinant() * changeSign(i) * changeSign(j)
            }
        }
        return mat
    }

    fun inverse(): Matrix<T> {
        return cofactor().transpose() * (1.0 / determinant())
    }

    operator fun times(v: T): Matrix<T> {
        val mat = copy()
        for (i in 0 until size()) {
            mat[i] = mat[i] * v
        }
        return mat
    }

    override fun times(d: Double): Matrix<T> {
        val mat = copy()
        for (i in 0 until size()) {
            mat[i] = mat[i] * d
        }
        return mat
    }

    override fun div(d: Double): Matrix<T> {
        val mat = copy()
        for (i in 0 until size()) {
            mat[i] = mat[i] / d
        }
        return mat
    }

    override fun plus(d: Double): Matrix<T> {
        val mat = copy()
        for (i in 0 until size()) {
            mat[i] = mat[i] + d
        }
        return mat
    }

    override fun minus(d: Double): Matrix<T> {
        val mat = copy()
        for (i in 0 until size()) {
            mat[i] = mat[i] - d
        }
        return mat
    }

    override fun minus(n: Matrix<T>): Matrix<T> {
        TODO("Not yet implemented")
    }

    override fun plus(n: Matrix<T>): Matrix<T> {
        TODO("Not yet implemented")
    }

    override fun times(n: Matrix<T>): Matrix<T> {
        TODO("Not yet implemented")
    }

    override fun div(n: Matrix<T>): Matrix<T> {
        TODO("Not yet implemented")
    }

    override fun unaryMinus(): Matrix<T> {
        TODO("Not yet implemented")
    }

    override fun abs(): Matrix<T> {
        TODO("Not yet implemented")
    }

    override fun one(): Matrix<T> {
        return Companion.one(size(), numericType)
    }

    override fun zero(): Matrix<T> {
        return Companion.zero(size(), numericType)
    }

    override fun self(): Matrix<T> {
        return this
    }

    override fun copy(): Matrix<T> {
        val copy = zero()
        for (i in 0 until size()) {
            copy[i] = row(i).copy()
        }
        return copy
    }

    override fun compareTo(other: Matrix<T>): Int {
        TODO("Not yet implemented")
    }

    override fun <T : Numeric<T>> Double.div(t: T): T {
        TODO("Not yet implemented")
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Matrix<*>

        if (!m.contentEquals(other.m)) return false

        return true
    }

    override fun hashCode(): Int {
        return m.contentHashCode()
    }

    override fun toString(): String {
        return "Matrix(m=${m.contentToString()})"
    }


}
