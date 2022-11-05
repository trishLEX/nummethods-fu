package ru.fa.systems

class Gauss(
    val matrix: Matrix,
    val vector: Vector
) {

    fun evaluate(): Vector {
        var a = matrix.copy()
        var b = vector.copy()

        for (k in 0 until matrix.size()) {
            var ak = a.copy()
            var bk = b.copy()
            for (i in k + 1 until  matrix.size()) {
                var mi = a[i][k] / a[k][k]
                var bi = b[i] - mi * b[k]

                var ai = arrayOf(0.0, 0.0, 0.0)
                for (j in k until matrix.size()) {
                    var aij = a[i][j] - mi * a[k][j]
                    ai[j] = aij
                }
                ak[i] = Vector(ai)
                bk[i] = bi
            }
            a = ak
            b = bk
        }

        println(a)
        println(b)

        var x = Vector(
            arrayOf(
                0.0,
                0.0,
                b[b.size() - 1] / a[b.size() - 1][b.size() - 1]
            )
        )
        for (n in b.size() - 2 downTo 0) {
            var xn = b[n]
            for (j in n + 1 until  b.size()) {
                xn -= a[n][j] * x[j]
            }
            x[n] = xn / a[n][n]
        }
        return x
    }
}