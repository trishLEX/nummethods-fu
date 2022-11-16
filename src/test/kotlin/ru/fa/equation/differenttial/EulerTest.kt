package ru.fa.equation.differenttial

import org.junit.jupiter.api.Test
import ru.fa.Value
import ru.fa.equation.differential.Euler
import ru.fa.equation.differential.Point
import ru.fa.equation.differential.RungeKutta

class EulerTest {

    @Test
    fun testEuler() {
        val derivative: (Point) -> Value = {it.x * it.x - it.y * 2.0}
        val start = Point(Value(0.0), Value(1.0))
        val res = Euler(derivative, start, 0.1, 10).evaluate()
        println(res)
    }

    @Test
    fun testRungeKutta() {
        val derivative: (Point) -> Value = {it.x * it.x - it.y * 2.0}
        val start = Point(Value(0.0), Value(1.0))
        val res = RungeKutta(derivative, start, 0.1, 10).evaluate()
        println(res)
    }
}