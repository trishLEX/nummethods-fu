package ru.fa.integration

import ru.fa.MethodResult
import ru.fa.Value

/**
 * Реализация интегрирования
 *
 * @param function функция, которую необходимо проинтегрировать
 * @param start    начало определенного интеграла
 * @param end      конец определенного интеграла
 */
class Integration(
    private val function: (Value) -> Value,
    private val start: Value,
    private val end: Value
) {

    /**
     * Посчитать определенный интеграл методом трапеций
     *
     * @param n          кол-во разбиений площади под функцией
     * @param derivative функция производной
     */
    fun trapeze(n: Int, derivative: (Value) -> Value): MethodResult {
        return TrapezeMethod(function, derivative, start, end, n).evaluate()
    }
}