package ru.fa.equation

import org.junit.jupiter.api.Test
import ru.fa.Value

class IterationsTest {

    @Test
    fun test() {
        val function: (Value) -> Value = {
            x -> x * x - x * 2
        }

        val derivative: (Value) -> Value = {
                x -> x * 2 - 1.0
        }

        val start = Value(-2.0, 0.0)
        val end = Value(0.5, 0.0)

        val res = Iterations(function, derivative, start, end).evaluate()
        println(res)
    }
}