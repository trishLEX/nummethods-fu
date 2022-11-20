package ru.fa.systems

import ru.fa.model.Matrix
import ru.fa.model.Method
import ru.fa.model.MethodResult
import ru.fa.model.NumericType
import ru.fa.model.Value
import ru.fa.model.Vector

class Gauss(
    private val matrix: Matrix<Value>,
    private val vector: Vector<Value>
) : Method<Vector<Value>> {

    override fun evaluate(): MethodResult<Vector<Value>> {
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
                val mi = ak[i][k] / ak[k][k]
                val bi = bk[i] - mi * bk[k]

                val ai = Vector.zero(a.size(), NumericType.VECTOR_VALUE)
                for (j in k until matrix.size()) {
                    val aij = ak[i][j] - mi * ak[k][j]
                    ai[j] = aij
                }
                ak[i] = ai
                bk[i] = bi
            }
            a = ak
            b = bk
        }

        // обратный ход
        val x = Vector.zero(a.size(), NumericType.VECTOR_VALUE)
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

    private fun getMainRow(a: Matrix<Value>, k: Int): Int {
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