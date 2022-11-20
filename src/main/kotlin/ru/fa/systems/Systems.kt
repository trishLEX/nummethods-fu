package ru.fa.systems

import ru.fa.model.Matrix
import ru.fa.model.MethodResult
import ru.fa.model.Value
import ru.fa.model.Vector

/**
 * Реализация решения СЛАУ
 *
 * @param matrix матрица СЛАУ
 * @param vector свободный член СЛАУ
 */
class Systems(
    private val matrix: Matrix<Value>,
    private val vector: Vector<Value>
) {

    /**
     * Поиск решения СЛАУ методом Гаусса
     */
    fun gauss(): MethodResult<Vector<Value>> {
        return Gauss(matrix, vector).evaluate()
    }
}