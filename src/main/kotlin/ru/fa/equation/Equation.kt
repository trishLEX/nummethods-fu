package ru.fa.equation

import ru.fa.model.MethodResult
import ru.fa.model.Value

/**
 * Реализация решения уравнения
 *
 * @param function функция, корни которой необходимо найти
 */
class Equation(
    private val function: (Value) -> Value
) {

    companion object {
        private const val ACCURACY = 0.01
    }

    /**
     * Поиск корня методом последовательных итераций
     *
     * @param derivative функция производной
     * @param start      начало интервала для поиска корня
     * @param end        конец интервала для поиска корня
     * @param accuracy   требуемая точность вычислений
     */
    fun iterations(
        derivative: (Value) -> Value,
        start: Value,
        end: Value,
        accuracy: Double = ACCURACY
    ): MethodResult<Value> {
        return Iterations(function, derivative, start, end, accuracy).evaluate()
    }

    /**
     * Поиск корня улучшенным методом последовательных итераций
     *
     * @param derivative функция производной
     * @param start      начало интервала для поиска корня
     * @param end        конец интервала для поиска корня
     * @param accuracy   требуемая точность вычислений
     */
    fun improvedIterations(
        derivative: (Value) -> Value,
        start: Value,
        end: Value,
        accuracy: Double = ACCURACY
    ): MethodResult<Value> {
        return ImprovedIterations(function, derivative, start, end, accuracy).evaluate()
    }

    /**
     * Поиск корня методом Ньютона-Рафсона
     *
     * @param derivative функция производной
     * @param start      начало интервала для поиска корня
     * @param accuracy   требуемая точность вычислений
     */
    fun newtonRaphson(
        derivative: (Value) -> Value,
        start: Value,
        accuracy: Double = ACCURACY
    ): MethodResult<Value> {
        return NewtonRaphson(function, derivative, start, accuracy).evaluate()
    }
}