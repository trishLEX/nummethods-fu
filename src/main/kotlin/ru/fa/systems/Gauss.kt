package ru.fa.systems

import ru.fa.Method
import ru.fa.MethodResult

class Gauss(
    private val matrix: Matrix,
    private val vector: Vector
) : Method<Vector> {

    override fun evaluate(): MethodResult<Vector> {
        if (matrix.size() != vector.size()) {
            throw IllegalArgumentException("Different sizes of matrix and vector")
        }

        var a = matrix.copy()
        var b = vector.copy()

        // прямой ход
        for (k in 0 until matrix.size()) {
            // выбираем главный элемент
            val mainRow = getMainRow(a, k)
            val ak = a.changeRows(k, mainRow)
            val bk = b.changeElements(k, mainRow)

            for (i in k + 1 until  matrix.size()) {
                val mi = a[i][k] / a[k][k]
                val bi = b[i] - mi * b[k]

                val ai = Array(a.size()) { 0.0 }
                for (j in k until matrix.size()) {
                    val aij = a[i][j] - mi * a[k][j]
                    ai[j] = aij
                }
                ak[i] = Vector(ai)
                bk[i] = bi
            }
            a = ak
            b = bk
        }

        // обратный ход
        val x = Vector(Array(a.size()) { 0.0 })
        x[a.size() - 1] = b[b.size() - 1] / a[b.size() - 1][b.size() - 1]
        for (n in b.size() - 2 downTo 0) {
            var xn = b[n]
            for (j in n + 1 until  b.size()) {
                xn -= a[n][j] * x[j]
            }
            x[n] = xn / a[n][n]
        }
        return MethodResult(x, matrix.size())
    }

    private fun getMainRow(a: Matrix, k: Int): Int {
        var maxElement = a[k][k]
        var maxRow = k
        for (i in k + 1 until a.size()) {
            if (a[i][k] > maxElement) {
                maxElement = a[i][k]
                maxRow = i
            }
        }
        return maxRow
    }
}