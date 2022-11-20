package ru.fa.systems

import ru.fa.model.MethodResult
import ru.fa.model.Value

/**
 * Реализация решения СЛАУ
 *
 * @param matrix матрица СЛАУ
 * @param vector свободный член СЛАУ
 */
class Systems(
    private val matrix: ru.fa.model.Matrix<Value>,
    private val vector: ru.fa.model.Vector<Value>
) {

    /**
     * Поиск решения СЛАУ методом Гаусса
     */
    fun gauss(): MethodResult<ru.fa.model.Vector<Value>> {
        return Gauss(matrix, vector).evaluate()
    }
}