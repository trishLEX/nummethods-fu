package ru.fa.systems

data class Matrix(val m: Array<Vector>) {

    constructor(n: Int): this(Array(n) { Vector(it) })

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

    fun transpose(): Matrix {
        val transposedMatrix = Matrix(size())
        for (i in 0 until size()) {
            for (j in 0 until size()) {
                transposedMatrix[j][i] = this[i][j]
            }
        }
        return transposedMatrix
    }

    fun determinant(): Double {
        if (size() == 1) {
            return this[0][0]
        }
        if (size() == 2) {
            return this[0][0] * this[1][1] - this[0][1] * this[1][0]
        }
        var sum = 0.0
        for (i in 0 until size()) {
            val mul = if (i % 2 == 0) 1 else -1
            sum += mul * this[0][i] * createSubMatrix(0, i).determinant()
        }
        return sum
    }

    fun createSubMatrix(excludingRow: Int, excludingCol: Int): Matrix {
        val mat = Matrix(size() - 1)
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

    fun cofactor(): Matrix {
        val mat = Matrix(size())
        for (i in 0 until size()) {
            for (j in 0 until size()) {
                mat[i][j] = changeSign(i) * changeSign(j) * createSubMatrix(i, j).determinant()
            }
        }
        return mat
    }

    fun inverse(): Matrix {
        return cofactor().transpose() * (1.0 / determinant())
    }

    operator fun times(d: Double): Matrix {
        return Matrix(copy().m.map { it * d }.toTypedArray())
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

    operator fun times(vec: Vector): Vector {
        val res = Vector(vec.size())
        for (i in 0 until size()) {
            val row = m[i]
            var resRow = 0.0
            for (j in 0 until size()) {
                resRow += row[j] * vec[j]
            }
            res[i] = resRow
        }
        return res
    }
}

fun changeSign(i: Int) = if(i % 2 == 0) 1 else -1