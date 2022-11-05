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
                x -> x * 2 - 2.0
        }

        val start = Value(-3.0)
        val end = Value(0.9)

        val res = Equation(function).iterations(derivative, start, end)
        println("iterations: $res")
    }

    @Test
    fun testImprovedIterations() {
        val function: (Value) -> Value = {
                x -> x * x - x * 2
        }

        val derivative: (Value) -> Value = {
                x -> x * 2 - 2.0
        }

        val start = Value(-3.0)
        val end = Value(0.9)
        val res = Equation(function).improvedIterations(derivative, start, end)
        println("improved iterations: $res")
    }

    @Test
    fun testNewton() {
        val function: (Value) -> Value = {
                x -> x * x - x * 2
        }

        val derivative: (Value) -> Value = {
                x -> x * 2 - 2.0
        }

        val start = Value(-3.0)
        val res = Equation(function).newtonRaphson(derivative, start)
        println("newton-raphson: $res")
    }
}