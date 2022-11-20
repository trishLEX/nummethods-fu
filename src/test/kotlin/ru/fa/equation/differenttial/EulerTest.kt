package ru.fa.equation.differenttial

import org.junit.jupiter.api.Test
import ru.fa.equation.differential.Euler
import ru.fa.equation.differential.RungeKutta
import ru.fa.model.Point
import ru.fa.model.Value

class EulerTest {

    //http://yotx.ru/#!1/3_h/sH@1v7Rgzhf23/aP9g/2DfT0qt7W8cQA54W5CD3Y0DyBbvAALb3TiA7PAOIKegrd2NA8jFwcHBwcHBwcHBwcHBwcEBiHcAOds6he1uHEBAvAMIaGcHBDo4ODg4ODg4ODg4AO1uHEDOeAeQi7OLy7Odg4ODg4ODg4ODA9DuxgHklHcAuTiAbZ1unR4cHBwcHBwcHIB2Nw4gCN4BZAe2c7ZzuQM7ODg4ODg4ODjb3TiAIC4vLy8vLy8vLy8vLy8vL3kHkB3E2cHOBWgHtLtxAIFdXl5eXl5eXl5eXl5eXl7yDiA7MNDBFgxxcbmzu7@zT6JhN7ZgCMbjAePxYHd/a39/4wBywNuCHOxuHEC2eAcQ2M7B2e7GAWSHdwA5RZztbO1uHEAuDg4ODg4ODg4ODg4ODg4OQLwDyNnZJQKxs3V5eXl5eXl5ebm7cQAB8Q4gIMTB6dbFzsHF5eXl5eXl5dnuxgHkjHcAAR2AYAc7sB3EDuLy8vLyErS7cQA55R1ALs4uLy9gF1uwi9PLs8vLS9DuxgEEwTuAXFyADkCXoJ2tg9OLA8TW5dnuxgEEcXl5eXl5eXl5eXl5eXl5yTuAXOycne0cnO2c7SC2EFuXuxsHENjl5eXl5eXl5eXl5eXl5SXvAHJxcXGwcwq7OEDs7MAOLk9393f2STTsBuiA8bgD2mI8gg5297f29y@2QeC19Y3Nnb1d6AHkbG99Z/MAcrYHPYDsnO0f7JNo2I2dU8bj6RbjcevyYnd/ax8D

    @Test
    fun testEuler() {
        val derivative: (Point) -> Value = {it.x * it.x - it.y * 2.0}
        val start = Point(0.0, 1.0)
        val res = Euler(derivative, start, 0.1, 10).evaluate()
        println(res.result.joinToString(" "))
    }

    @Test
    fun testRungeKutta() {
        val derivative: (Point) -> Value = {it.x * it.x - it.y * 2.0}
        val start = Point(0.0, 1.0)
        val res = RungeKutta(derivative, start, 0.1, 10).evaluate()
        println(res.result.joinToString(" "))
    }
}