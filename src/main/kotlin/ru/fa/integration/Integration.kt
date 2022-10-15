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

    companion object {
        private const val DEFAULT_N = 10
    }

    /**
     * Посчитать определенный интеграл методом трапеций
     *
     * @param n          кол-во разбиений площади под функцией
     * @param derivative функция производной
     */
    fun trapeze(n: Int, derivative: (Value) -> Value): MethodResult {
        return TrapezeMethod(function, derivative, start, end, n).evaluate()
    }

    /**
     * Посчитать определенный интеграл методом трапеций с требуемой точностью,
     * используя правило остановки счета по Рунге
     *
     * @param error      требуемая точность
     * @param derivative функция производной
     */
    fun trapeze(error: Double, derivative: (Value) -> Value): MethodResult {
        var n = DEFAULT_N
        var res = TrapezeMethod(function, derivative, start, end, n).evaluate()
        while (res.methodError > error) {
            n *= 2
            res = TrapezeMethod(function, derivative, start, end, n).evaluate()
        }
        return res
    }
}