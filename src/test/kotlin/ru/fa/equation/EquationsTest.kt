package ru.fa.equation

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import ru.fa.model.MethodResult
import ru.fa.model.Value

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
        Assertions.assertEquals(
            MethodResult(Value(-0.009999037108959849), 2462),
            res
        )
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
        Assertions.assertEquals(
            MethodResult(Value(0.0022154507153729287), 4),
            res
        )
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
        Assertions.assertEquals(
            MethodResult(Value(2.8211101319785935E-4), 3),
            res
        )
    }
}