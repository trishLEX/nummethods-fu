package ru.fa.systems

import ru.fa.model.MethodResult
import ru.fa.model.ValueNew

/**
 * Реализация решения СЛАУ
 *
 * @param matrix матрица СЛАУ
 * @param vector свободный член СЛАУ
 */
class Systems(
    private val matrix: ru.fa.model.Matrix<ValueNew>,
    private val vector: ru.fa.model.Vector<ValueNew>
) {

    /**
     * Поиск решения СЛАУ методом Гаусса
     */
    fun gauss(): MethodResult<ru.fa.model.Vector<ValueNew>> {
        return Gauss(matrix, vector).evaluate()
    }
}