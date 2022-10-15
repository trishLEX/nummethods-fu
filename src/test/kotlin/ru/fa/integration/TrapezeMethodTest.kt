package ru.fa.integration

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import ru.fa.MethodResult
import ru.fa.Value
import ru.fa.div
import ru.fa.times

class TrapezeMethodTest {

    @Test
    fun test() {
        val function: (Value) -> Value = { x -> 7.0 / (x * x + 1.0)}
        val derivative: (Value) -> Value = { x -> -14.0 * x / (x * x + 1.0).pow(2)}
        val measureError = 0.01
        val start = Value(0.0, measureError)
        val end = Value(5.0, measureError)
        val n = 10

        val res = TrapezeMethod(function, derivative, start, end, n).evaluate()
        Assertions.assertEquals(
            MethodResult(
                Value(9.611728568610765, 0.05506706507174948),
                0.002157297830374753
            ),
            res
        )
    }

    @Test
    fun testRunge() {
        val function: (Value) -> Value = { x -> 7.0 / (x * x + 1.0)}
        val derivative: (Value) -> Value = { x -> -14.0 * x / (x * x + 1.0).pow(2)}
        val measureError = 0.01
        val start = Value(0.0, measureError)
        val end = Value(5.0, measureError)

        val res = Integration(function, start, end).trapeze(0.001, derivative)
        Assertions.assertEquals(
            MethodResult(
                Value(9.613266283413711, 0.029748202966649234),
                5.393244575936883E-4
            ),
            res
        )
    }
}