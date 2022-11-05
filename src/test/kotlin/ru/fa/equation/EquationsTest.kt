package ru.fa.equation

import org.junit.jupiter.api.Test
import ru.fa.Value

class EquationsTest {

    @Test
    fun testIterations() {
        val function: (Value) -> Value = {
            x -> x * x - x * 2
        }

        val derivative: (Value) -> Value = {
                x -> x * 2 - 1.0
        }

        val start = Value(-3.0)
        val end = Value(0.9)

        val res = Iterations(function, derivative, start, end).evaluate()
        println("iterations: $res")
    }

    @Test
    fun testImprovedIterations() {
        val function: (Value) -> Value = {
                x -> x * x - x * 2
        }

        val derivative: (Value) -> Value = {
                x -> x * 2 - 1.0
        }

        val start = Value(-5.0)
        val end = Value(1.0)
        val res = ImprovedIterations(function, derivative, start, end).evaluate()
        println("improved iterations: $res")
    }

    @Test
    fun testNewton() {
        val function: (Value) -> Value = {
                x -> x * x - x * 2
        }

        val derivative: (Value) -> Value = {
                x -> x * 2 - 1.0
        }

        val start = Value(-3.0)
        val res = NewtonRaphson(function, derivative, start).evaluate()
        println("newton-raphson: $res")
    }
}