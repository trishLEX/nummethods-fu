package ru.fa.equation.differenttial

import org.junit.jupiter.api.Test
import ru.fa.equation.differential.Euler
import ru.fa.equation.differential.RungeKutta
import ru.fa.model.Point
import ru.fa.model.Value

class EulerTest {

    @Test
    fun testEuler() {
        val derivative: (Point) -> Value = {it.x * it.x - it.y * 2.0}
        val start = Point(0.0, 1.0)
        val res = Euler(derivative, start, 0.1, 10).evaluate()
        println(res.result)
    }

    @Test
    fun testRungeKutta() {
        val derivative: (Point) -> Value = {it.x * it.x - it.y * 2.0}
        val start = Point(0.0, 1.0)
        val res = RungeKutta(derivative, start, 0.1, 10).evaluate()
        println(res.result)
    }
}