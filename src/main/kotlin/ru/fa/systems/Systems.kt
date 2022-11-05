package ru.fa.systems

import ru.fa.MethodResult

/**
 * Реализация решения СЛАУ
 *
 * @param matrix матрица СЛАУ
 * @param vector свободный член СЛАУ
 */
class Systems(
    private val matrix: Matrix,
    private val vector: Vector
) {

    /**
     * Поиск решения СЛАУ методом Гаусса
     */
    fun gauss(): MethodResult<Vector> {
        return Gauss(matrix, vector).evaluate()
    }
}